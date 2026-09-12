package com.smartpg.test;

import com.smartpg.dao.PaymentDAO;

public class TestPaymentDelete {

    public static void main(String[] args) {

        PaymentDAO dao = new PaymentDAO();

        dao.deletePayment(1); // এখানে Existing Payment ID দেবে

    }
}