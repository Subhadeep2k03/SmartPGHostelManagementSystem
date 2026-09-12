package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.Payment;
import com.smartpg.util.DBConnection;

public class PaymentDAO {

    // ADD PAYMENT
    public boolean addPayment(Payment payment) {

        boolean status = false;

        String sql = "INSERT INTO payment(student_id, amount, payment_date, payment_status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payment.getStudentId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentDate());
            ps.setString(4, payment.getStatus());

            int result = ps.executeUpdate();

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // VIEW ALL PAYMENTS
    public List<Payment> getAllPayments() {

        List<Payment> list = new ArrayList<>();

        String query = "SELECT * FROM payment";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setStudentId(rs.getInt("student_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(rs.getString("payment_date"));
                payment.setStatus(rs.getString("payment_status"));

                list.add(payment);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // VIEW PAYMENTS FOR ONE STUDENT (Student portal - read-only).
    // Caller must pass a studentId taken from the authenticated session,
    // never from a request parameter, so a student can only ever see
    // their own payment rows.
    public List<Payment> getPaymentsByStudentId(int studentId) {

        List<Payment> list = new ArrayList<>();

        String query = "SELECT * FROM payment WHERE student_id=? ORDER BY payment_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Payment payment = new Payment();

                    payment.setPaymentId(rs.getInt("payment_id"));
                    payment.setStudentId(rs.getInt("student_id"));
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPaymentDate(rs.getString("payment_date"));
                    payment.setStatus(rs.getString("payment_status"));

                    list.add(payment);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // UPDATE PAYMENT
    public boolean updatePayment(Payment payment) {

        boolean status = false;

        String query = "UPDATE payment SET amount=?, payment_date=?, payment_status=? WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDouble(1, payment.getAmount());
            ps.setString(2, payment.getPaymentDate());
            ps.setString(3, payment.getStatus());
            ps.setInt(4, payment.getPaymentId());

            int result = ps.executeUpdate();

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // DELETE PAYMENT
    public boolean deletePayment(int paymentId) {

        boolean status = false;

        String query = "DELETE FROM payment WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, paymentId);

            int result = ps.executeUpdate();

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

}
