package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.Complaint;
import com.smartpg.util.DBConnection;

public class ComplaintDAO {

    // INSERT COMPLAINT
    public void insertComplaint(Complaint complaint) {

        String sql = "insert into complaint(student_id, room_id, complaint_date, description, status) values(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, complaint.getStudentId());
            ps.setInt(2, complaint.getRoomId());
            ps.setString(3, complaint.getComplaintDate());
            ps.setString(4, complaint.getDescription());
            ps.setString(5, complaint.getStatus());

            ps.executeUpdate();

            System.out.println("Complaint inserted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL COMPLAINTS
    public List<Complaint> getAllComplaints() {

        List<Complaint> list = new ArrayList<>();

        String sql = "select * from complaint";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Complaint c = new Complaint();

                c.setComplaintId(rs.getInt("complaint_id"));
                c.setStudentId(rs.getInt("student_id"));
                c.setRoomId(rs.getInt("room_id"));
                c.setComplaintDate(rs.getString("complaint_date"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));

                list.add(c);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // VIEW COMPLAINTS FOR ONE STUDENT (Student portal).
    // studentId must come from the authenticated session, never from a
    // request parameter, so a student can only see their own complaints.
    public List<Complaint> getComplaintsByStudentId(int studentId) {

        List<Complaint> list = new ArrayList<>();

        String sql = "select * from complaint where student_id=? order by complaint_date desc";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Complaint c = new Complaint();

                    c.setComplaintId(rs.getInt("complaint_id"));
                    c.setStudentId(rs.getInt("student_id"));
                    c.setRoomId(rs.getInt("room_id"));
                    c.setComplaintDate(rs.getString("complaint_date"));
                    c.setDescription(rs.getString("description"));
                    c.setStatus(rs.getString("status"));

                    list.add(c);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // GET COMPLAINT BY ID
    public Complaint getComplaintById(int complaintId) {

        Complaint complaint = null;

        String sql = "select * from complaint where complaint_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, complaintId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    complaint = new Complaint();

                    complaint.setComplaintId(rs.getInt("complaint_id"));
                    complaint.setStudentId(rs.getInt("student_id"));
                    complaint.setRoomId(rs.getInt("room_id"));
                    complaint.setComplaintDate(rs.getString("complaint_date"));
                    complaint.setDescription(rs.getString("description"));
                    complaint.setStatus(rs.getString("status"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return complaint;
    }

    // UPDATE COMPLAINT STATUS
    public void updateComplaint(Complaint complaint) {

        String sql = "update complaint set status=? where complaint_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, complaint.getStatus());
            ps.setInt(2, complaint.getComplaintId());

            ps.executeUpdate();

            System.out.println("Complaint updated successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // DELETE COMPLAINT
    public void deleteComplaint(int complaintId) {

        String sql = "delete from complaint where complaint_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, complaintId);

            ps.executeUpdate();

            System.out.println("Complaint deleted successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
