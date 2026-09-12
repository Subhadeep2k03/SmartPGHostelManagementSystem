package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.Visitor;
import com.smartpg.util.DBConnection;

public class VisitorDAO {

    // Add Visitor
    public boolean addVisitor(Visitor visitor) {

        boolean status = false;

        String sql = "INSERT INTO visitor(student_id, visitor_name, phone, relation, visit_date, in_time, out_time) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, visitor.getStudentId());
            ps.setString(2, visitor.getVisitorName());
            ps.setString(3, visitor.getPhone());
            ps.setString(4, visitor.getRelation());
            ps.setDate(5, visitor.getVisitDate());
            ps.setTime(6, visitor.getInTime());
            ps.setTime(7, visitor.getOutTime());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // View All Visitors
    public List<Visitor> getAllVisitors() {

        List<Visitor> list = new ArrayList<>();

        String sql = "SELECT * FROM visitor ORDER BY visitor_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Visitor visitor = new Visitor();

                visitor.setVisitorId(rs.getInt("visitor_id"));
                visitor.setStudentId(rs.getInt("student_id"));
                visitor.setVisitorName(rs.getString("visitor_name"));
                visitor.setPhone(rs.getString("phone"));
                visitor.setRelation(rs.getString("relation"));
                visitor.setVisitDate(rs.getDate("visit_date"));
                visitor.setInTime(rs.getTime("in_time"));
                visitor.setOutTime(rs.getTime("out_time"));

                list.add(visitor);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // VIEW VISITORS FOR ONE STUDENT (Student portal - read-only).
    // studentId must come from the authenticated session, never from a
    // request parameter, so a student can only see their own visitors.
    public List<Visitor> getVisitorsByStudentId(int studentId) {

        List<Visitor> list = new ArrayList<>();

        String sql = "SELECT * FROM visitor WHERE student_id=? ORDER BY visit_date DESC, in_time DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Visitor visitor = new Visitor();

                    visitor.setVisitorId(rs.getInt("visitor_id"));
                    visitor.setStudentId(rs.getInt("student_id"));
                    visitor.setVisitorName(rs.getString("visitor_name"));
                    visitor.setPhone(rs.getString("phone"));
                    visitor.setRelation(rs.getString("relation"));
                    visitor.setVisitDate(rs.getDate("visit_date"));
                    visitor.setInTime(rs.getTime("in_time"));
                    visitor.setOutTime(rs.getTime("out_time"));

                    list.add(visitor);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // Get Visitor By ID
    public Visitor getVisitorById(int id) {

        Visitor visitor = null;

        String sql = "SELECT * FROM visitor WHERE visitor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    visitor = new Visitor();

                    visitor.setVisitorId(rs.getInt("visitor_id"));
                    visitor.setStudentId(rs.getInt("student_id"));
                    visitor.setVisitorName(rs.getString("visitor_name"));
                    visitor.setPhone(rs.getString("phone"));
                    visitor.setRelation(rs.getString("relation"));
                    visitor.setVisitDate(rs.getDate("visit_date"));
                    visitor.setInTime(rs.getTime("in_time"));
                    visitor.setOutTime(rs.getTime("out_time"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return visitor;
    }

    // Update Visitor
    public boolean updateVisitor(Visitor visitor) {

        boolean status = false;

        String sql = "UPDATE visitor SET student_id=?, visitor_name=?, phone=?, relation=?, visit_date=?, in_time=?, out_time=? WHERE visitor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, visitor.getStudentId());
            ps.setString(2, visitor.getVisitorName());
            ps.setString(3, visitor.getPhone());
            ps.setString(4, visitor.getRelation());
            ps.setDate(5, visitor.getVisitDate());
            ps.setTime(6, visitor.getInTime());
            ps.setTime(7, visitor.getOutTime());
            ps.setInt(8, visitor.getVisitorId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // Delete Visitor
    public boolean deleteVisitor(int id) {

        boolean status = false;

        String sql = "DELETE FROM visitor WHERE visitor_id=?";

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
