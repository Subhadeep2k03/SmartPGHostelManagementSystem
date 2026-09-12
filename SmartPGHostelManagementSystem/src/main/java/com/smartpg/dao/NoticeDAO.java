package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.Notice;
import com.smartpg.util.DBConnection;

public class NoticeDAO {

    // Add Notice
    public boolean addNotice(Notice notice) {

        boolean status = false;

        String sql = "INSERT INTO notice(title, description, notice_date) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, notice.getTitle());
            ps.setString(2, notice.getDescription());
            ps.setDate(3, notice.getNoticeDate());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // View All Notices
    public List<Notice> getAllNotices() {

        List<Notice> list = new ArrayList<>();

        String sql = "SELECT * FROM notice ORDER BY notice_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Notice notice = new Notice();

                notice.setNoticeId(rs.getInt("notice_id"));
                notice.setTitle(rs.getString("title"));
                notice.setDescription(rs.getString("description"));
                notice.setNoticeDate(rs.getDate("notice_date"));

                list.add(notice);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // Get Notice By ID
    public Notice getNoticeById(int id) {

        Notice notice = null;

        String sql = "SELECT * FROM notice WHERE notice_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    notice = new Notice();

                    notice.setNoticeId(rs.getInt("notice_id"));
                    notice.setTitle(rs.getString("title"));
                    notice.setDescription(rs.getString("description"));
                    notice.setNoticeDate(rs.getDate("notice_date"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return notice;
    }

    // Update Notice
    public boolean updateNotice(Notice notice) {

        boolean status = false;

        String sql = "UPDATE notice SET title=?, description=?, notice_date=? WHERE notice_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, notice.getTitle());
            ps.setString(2, notice.getDescription());
            ps.setDate(3, notice.getNoticeDate());
            ps.setInt(4, notice.getNoticeId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // Delete Notice
    public boolean deleteNotice(int id) {

        boolean status = false;

        String sql = "DELETE FROM notice WHERE notice_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

}
