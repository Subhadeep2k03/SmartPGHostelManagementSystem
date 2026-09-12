package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.smartpg.util.DBConnection;
import com.smartpg.util.PasswordUtil;

public class AdminDAO {

    /**
     * Verifies admin credentials. The stored admin.password column holds a
     * PasswordUtil-encoded hash (see student_portal_migration.sql) rather
     * than a plaintext value - the query looks up by username only, and the
     * password comparison itself happens in Java via PasswordUtil so the
     * plaintext password never appears in SQL text or logs.
     */
    public boolean login(String username, String password) {

        boolean status = false;

        String sql = "select password from admin where username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String storedHash = rs.getString("password");

                    status = PasswordUtil.verifyPassword(password, storedHash);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}
