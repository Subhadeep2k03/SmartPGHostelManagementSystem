package com.smartpg.test;

import java.util.List;

import com.smartpg.dao.PaymentDAO;
import com.smartpg.model.Payment;

public class TestPaymentRead {

    public static void main(String[] args) {

        PaymentDAO dao = new PaymentDAO();

        List<Payment> paymentList = dao.getAllPayments();

        for (Payment payment : paymentList) {

            System.out.println("Payment ID     : " + payment.getPaymentId());
            System.out.println("Student ID     : " + payment.getStudentId());
            System.out.println("Amount         : " + payment.getAmount());
            System.out.println("Payment Date   : " + payment.getPaymentDate());
            System.out.println("Payment Status : " + payment.getStatus());

            System.out.println("----------------------------------------");
        }
    }
}