package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.Student;
import com.smartpg.util.DBConnection;
import com.smartpg.util.PasswordUtil;

public class StudentDAO {

    // ---------------------------------------------------------------
    // Student self-service authentication (registration/login).
    // Kept in this DAO, next to the existing student CRUD methods,
    // rather than in a separate DAO class - same table, same connection
    // handling, no need for a parallel class.
    // ---------------------------------------------------------------

    /** True if a student row already exists with this email (case-insensitive). */
    public boolean isEmailTaken(String email) {

        String sql = "SELECT student_id FROM student WHERE LOWER(email)=LOWER(?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /** True if a student row already exists with this phone number. */
    public boolean isPhoneTaken(String phone) {

        String sql = "SELECT student_id FROM student WHERE phone=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phone);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registers a new student account. The plaintext password is hashed
     * with PasswordUtil before it ever reaches SQL; only the hash is stored,
     * in the migration-added student.password_hash column. student.status
     * is always set to "Active" here (server-side), never taken from the
     * registration form.
     */
    public boolean registerStudent(Student student, String plainPassword) {

        boolean status = false;

        String hashed = PasswordUtil.hashPassword(plainPassword);

        String sql = "INSERT INTO student(first_name,last_name,gender,dob,phone,email,address," +
                     "guardian_name,guardian_phone,join_date,status,password_hash) " +
                     "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getDob());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getAddress());
            ps.setString(8, student.getGuardianName());
            ps.setString(9, student.getGuardianPhone());
            ps.setString(10, student.getJoinDate());
            ps.setString(11, "Active");
            ps.setString(12, hashed);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * Verifies a student login by email + password. Returns the full
     * Student row (so the servlet can read studentId for the session) on
     * success, or null on any failure (unknown email, wrong password, or
     * an account with no password_hash yet, e.g. pre-migration data).
     */
    public Student loginStudent(String email, String plainPassword) {

        String sql = "SELECT * FROM student WHERE LOWER(email)=LOWER(?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String storedHash = rs.getString("password_hash");

                    if (PasswordUtil.verifyPassword(plainPassword, storedHash)) {

                        Student student = new Student();

                        student.setStudentId(rs.getInt("student_id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setGender(rs.getString("gender"));
                        student.setDob(rs.getString("dob"));
                        student.setPhone(rs.getString("phone"));
                        student.setEmail(rs.getString("email"));
                        student.setAddress(rs.getString("address"));
                        student.setGuardianName(rs.getString("guardian_name"));
                        student.setGuardianPhone(rs.getString("guardian_phone"));
                        student.setJoinDate(rs.getString("join_date"));
                        student.setStatus(rs.getString("status"));

                        return student;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addStudent(Student student) {

        String sql = "INSERT INTO student(first_name,last_name,gender,dob,phone,email,address,guardian_name,guardian_phone,join_date,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getDob());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getAddress());
            ps.setString(8, student.getGuardianName());
            ps.setString(9, student.getGuardianPhone());
            ps.setString(10, student.getJoinDate());
            ps.setString(11, student.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Added Successfully");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM student";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setGender(rs.getString("gender"));
                student.setDob(rs.getString("dob"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student.setGuardianName(rs.getString("guardian_name"));
                student.setGuardianPhone(rs.getString("guardian_phone"));
                student.setJoinDate(rs.getString("join_date"));
                student.setStatus(rs.getString("status"));

                students.add(student);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return students;
    }

    public void updateStudent(Student student) {

        String sql = "UPDATE student SET first_name=?, last_name=?, phone=?, email=?, address=?, status=? WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getStatus());
            ps.setInt(7, student.getStudentId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Updated Successfully");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {

        String deleteAllocation = "DELETE FROM room_allocation WHERE student_id=?";
        String deleteStudent = "DELETE FROM student WHERE student_id=?";

        try (Connection con = DBConnection.getConnection()) {

            try (PreparedStatement ps1 = con.prepareStatement(deleteAllocation)) {
                ps1.setInt(1, studentId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(deleteStudent)) {
                ps2.setInt(1, studentId);

                int rows = ps2.executeUpdate();

                if (rows > 0) {
                    System.out.println("Student Deleted Successfully");
                } else {
                    System.out.println("Student Not Found");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public Student getStudentById(int studentId) {

        Student student = null;

        String sql = "SELECT * FROM student WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    student = new Student();

                    student.setStudentId(rs.getInt("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setGender(rs.getString("gender"));
                    student.setDob(rs.getString("dob"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
                    student.setAddress(rs.getString("address"));
                    student.setGuardianName(rs.getString("guardian_name"));
                    student.setGuardianPhone(rs.getString("guardian_phone"));
                    student.setJoinDate(rs.getString("join_date"));
                    student.setStatus(rs.getString("status"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

}
