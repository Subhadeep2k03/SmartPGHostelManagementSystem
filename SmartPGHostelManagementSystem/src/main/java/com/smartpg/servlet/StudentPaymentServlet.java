package com.smartpg.servlet;

import java.io.IOException;
import java.util.List;

import com.smartpg.dao.PaymentDAO;
import com.smartpg.model.Payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Read-only view of the logged-in student's own payments. studentId comes
 * from the session only. There is intentionally no add/edit/delete action
 * here - payment records stay Admin-managed, per the approved plan.
 */
@WebServlet("/student/StudentPaymentServlet")
public class StudentPaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Integer studentId = (Integer) session.getAttribute("studentId");

        PaymentDAO dao = new PaymentDAO();

        List<Payment> payments = dao.getPaymentsByStudentId(studentId);

        request.setAttribute("payments", payments);

        request.getRequestDispatcher("/student/studentPayments.jsp")
               .forward(request, response);
    }
}
