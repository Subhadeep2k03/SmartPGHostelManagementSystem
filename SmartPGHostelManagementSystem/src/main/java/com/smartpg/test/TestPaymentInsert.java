package com.smartpg.test;

import com.smartpg.dao.PaymentDAO;
import com.smartpg.model.Payment;

public class TestPaymentInsert {

    public static void main(String[] args) {

        Payment payment = new Payment();

        payment.setStudentId(5);
        payment.setAmount(5000);
        payment.setPaymentDate("2026-08-03");
        payment.setStatus("Paid");

        PaymentDAO dao = new PaymentDAO();
        dao.addPayment(payment);

    }
}