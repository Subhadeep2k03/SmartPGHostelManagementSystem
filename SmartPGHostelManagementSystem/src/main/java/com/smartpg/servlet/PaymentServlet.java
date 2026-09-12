package com.smartpg.servlet;

import java.io.IOException;

import com.smartpg.dao.PaymentDAO;
import com.smartpg.model.Payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = request.getParameter("action");


        Payment payment = new Payment();

        PaymentDAO dao = new PaymentDAO();



        // ADD PAYMENT

        if (action == null || action.equals("add")) {


            payment.setStudentId(
                    Integer.parseInt(request.getParameter("studentId")));



            payment.setAmount(
                    Double.parseDouble(request.getParameter("amount")));



            payment.setPaymentDate(
                    request.getParameter("paymentDate"));



            payment.setStatus(
                    request.getParameter("status"));



            dao.addPayment(payment);



            request.getSession().setAttribute(
                    "message",
                    "Payment Added Successfully"
            );


        }





        // UPDATE PAYMENT

        else if ("update".equals(action)) {



            payment.setPaymentId(
                    Integer.parseInt(request.getParameter("paymentId")));



            payment.setAmount(
                    Double.parseDouble(request.getParameter("amount")));



            payment.setPaymentDate(
                    request.getParameter("paymentDate"));



            payment.setStatus(
                    request.getParameter("status"));



            dao.updatePayment(payment);



            request.getSession().setAttribute(
                    "message",
                    "Payment Updated Successfully"
            );


        }



        response.sendRedirect(
                request.getContextPath() + "/payment/viewPayments.jsp");

    }






    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String action = request.getParameter("action");



        PaymentDAO dao = new PaymentDAO();




        // DELETE PAYMENT

        if ("delete".equals(action)) {



            int paymentId = Integer.parseInt(
                    request.getParameter("id"));



            dao.deletePayment(paymentId);



            request.getSession().setAttribute(
                    "message",
                    "Payment Deleted Successfully"
            );



            response.sendRedirect(
                    request.getContextPath() + "/payment/viewPayments.jsp");

        }





        // VIEW PAYMENT LIST

        else {


            response.sendRedirect(
                    request.getContextPath() + "/payment/viewPayments.jsp");

        }


    }

}