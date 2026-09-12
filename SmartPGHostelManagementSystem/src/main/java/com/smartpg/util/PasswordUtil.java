package com.smartpg.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Password hashing utility used by BOTH the Admin login (admin.password,
 * widened by the migration) and the new Student login (student.password_hash).
 *
 * Algorithm: PBKDF2WithHmacSHA256, 65536 iterations, 256-bit derived key,
 * 128-bit random salt per password.
 *
 * Stored format (single VARCHAR column):
 *     iterations:base64(salt):base64(hash)
 * e.g. "65536:LuVsxyXdeXxZLv60bjNb3A==:5GdkktcIXq1Ss4QYiUb/3/oyxufTgru1nHuKmojwf0Y="
 *
 * Storing the iteration count alongside the hash lets the work factor be
 * increased later without invalidating passwords hashed under an older
 * setting - verify() always re-reads the iteration count from the stored
 * value rather than assuming the current constant.
 */
public final class PasswordUtil {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH_BITS = 256;
    private static final int SALT_LENGTH_BYTES = 16;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    private PasswordUtil() {
        // utility class - no instances
    }

    /**
     * Hashes a plaintext password with a freshly generated random salt.
     * Returns the encoded "iterations:salt:hash" string to store in the DB.
     */
    public static String hashPassword(String plainPassword) {

        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        try {
            byte[] salt = new byte[SALT_LENGTH_BYTES];
            new SecureRandom().nextBytes(salt);

            byte[] hash = pbkdf2(plainPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH_BITS);

            return ITERATIONS + ":" +
                    Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Unable to hash password", e);
        }
    }

    /**
     * Verifies a plaintext password against a previously stored encoded hash.
     * Returns false (never throws) for null input or a malformed/legacy
     * stored value, so a row that isn't in PBKDF2 format simply never matches.
     */
    public static boolean verifyPassword(String plainPassword, String storedHash) {

        if (plainPassword == null || storedHash == null) {
            return false;
        }

        try {
            String[] parts = storedHash.split(":");

            if (parts.length != 3) {
                return false;
            }

            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[2]);

            byte[] actualHash = pbkdf2(plainPassword.toCharArray(), salt, iterations, expectedHash.length * 8);

            return slowEquals(expectedHash, actualHash);

        } catch (Exception e) {
            // Any parsing/algorithm problem => treat as "does not match".
            return false;
        }
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLengthBits)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLengthBits);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /** Constant-time byte comparison to avoid timing side channels. */
    private static boolean slowEquals(byte[] a, byte[] b) {

        int diff = a.length ^ b.length;

        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }
}
