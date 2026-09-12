package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.smartpg.util.DBConnection;

public class DashboardDAO {

    // Shared helper so every count query uses the same
    // connect -> query -> read -> close pattern.
    // Resources are always closed (even on error), which
    // prevents connections from leaking/piling up over time.
    private int getCount(String sql) {

        int count = 0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    public int getTotalStudents() {
        return getCount("SELECT COUNT(*) FROM student");
    }

    public int getTotalRooms() {
        return getCount("SELECT COUNT(*) FROM room");
    }

    public int getTotalPayments() {
        return getCount("SELECT COUNT(*) FROM payment");
    }

    public int getTotalComplaints() {
        return getCount("SELECT COUNT(*) FROM complaint");
    }

    // Visitor Count
    public int getTotalVisitors() {
        return getCount("SELECT COUNT(*) FROM visitor");
    }

    // Notice Count
    public int getTotalNotices() {
        return getCount("SELECT COUNT(*) FROM notice");
    }

}
