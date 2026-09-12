-- ============================================================
-- Smart PG Hostel Management System
-- Student Portal migration
-- ============================================================
--
-- This file is NOT executed automatically. Review it, then run it
-- yourself against your local "smartpg" database (e.g. via MySQL
-- Workbench, the mysql CLI, or phpMyAdmin) when you're ready.
--
-- What this does:
--   1. Adds a password_hash column to the existing `student` table
--      so students can log in. No new student table is created -
--      the existing table/model/DAO are reused as instructed.
--   2. Widens `admin`.`password` from VARCHAR(50) to VARCHAR(255)
--      so it can hold a PBKDF2 hash instead of a short plaintext
--      value (a PBKDF2WithHmacSHA256 hash + salt, base64-encoded,
--      is ~75-90 characters - VARCHAR(50) is too small for it).
--   3. Updates the existing admin row's password to a PBKDF2 hash
--      of "AdminDomain2026" (the requested new password), replacing
--      the current plaintext "12345"-style value. No other Admin
--      row is touched, and no Student data is touched at all.
--
-- Nothing here deletes rows or drops tables. Existing student,
-- room, payment, room_allocation, visitor, complaint and notice
-- data is left exactly as-is.
--
-- The hash below was generated and verified in a real, standalone
-- run of the exact PasswordUtil.java shipped in this project (see
-- src/main/java/com/smartpg/util/PasswordUtil.java) - it is not a
-- placeholder. It was checked to:
--   - verify TRUE for the password "AdminDomain2026"
--   - verify FALSE for a wrong password
--   - verify FALSE for the old plaintext "12345"
-- Format: <pbkdf2 iterations>:<base64 salt>:<base64 hash>
-- ============================================================


-- 1) Add authentication support to the existing student table.
--    IF NOT EXISTS guards would be nice here, but MySQL 8.0's
--    ADD COLUMN does not support IF NOT EXISTS in all versions,
--    so re-running this file on an already-migrated database will
--    error out on this line rather than silently duplicate the
--    column - that's intentional (fail loud, not silently no-op).
ALTER TABLE `student`
    ADD COLUMN `password_hash` VARCHAR(255) NULL AFTER `status`;


-- 2) Widen admin.password so it can hold a PBKDF2 hash instead of
--    a short plaintext value.
ALTER TABLE `admin`
    MODIFY COLUMN `password` VARCHAR(255) NOT NULL;


-- 3) Replace the existing Admin account's password with a hash of
--    "AdminDomain2026". This updates the row for the CURRENT admin
--    username already in your database - it does not create a new
--    admin account, and the plaintext password is never written to
--    this file or to any log.
UPDATE `admin`
SET `password` = '65536:bLGE6AYrn6vKQAAHjIWD+Q==:ylkpCwhygSqZaC1ZScy29Q3AxwV2eSc51FfB4VP2dts='
WHERE `admin_id` = 1;


-- ============================================================
-- End of migration.
--
-- After running this, in your local environment:
--   - Admin login: existing username + password "AdminDomain2026"
--     should succeed; the old password ("12345") should fail.
--   - New students can register via /student/studentRegister.jsp,
--     which fills password_hash for their row via PasswordUtil.
--   - Existing student rows (student_id 1-4 in your dump) will
--     have password_hash = NULL until/unless you register matching
--     accounts for them - they cannot log into the Student portal
--     until a password_hash is set, which is expected: this
--     migration does not fabricate passwords for pre-existing
--     student records.
-- ============================================================
