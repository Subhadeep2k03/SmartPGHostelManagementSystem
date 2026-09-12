package com.smartpg.test;

import com.smartpg.dao.PaymentDAO;
import com.smartpg.model.Payment;

public class TestPaymentUpdate {

    public static void main(String[] args) {

        Payment payment = new Payment();

        payment.setPaymentId(1); // Existing Payment ID
        payment.setAmount(6000);
        payment.setPaymentDate("2026-08-04");
        payment.setStatus("Paid");

        PaymentDAO dao = new PaymentDAO();
        dao.updatePayment(payment);
    }
}