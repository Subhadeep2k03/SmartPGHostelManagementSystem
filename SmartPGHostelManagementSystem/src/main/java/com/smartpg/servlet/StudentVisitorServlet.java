package com.smartpg.servlet;

import java.io.IOException;
import java.util.List;

import com.smartpg.dao.VisitorDAO;
import com.smartpg.model.Visitor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Read-only view of the logged-in student's own visitors. studentId comes
 * from the session only, never a request parameter.
 */
@WebServlet("/student/StudentVisitorServlet")
public class StudentVisitorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Integer studentId = (Integer) session.getAttribute("studentId");

        VisitorDAO dao = new VisitorDAO();

        List<Visitor> visitors = dao.getVisitorsByStudentId(studentId);

        request.setAttribute("visitors", visitors);

        request.getRequestDispatcher("/student/studentVisitors.jsp")
               .forward(request, response);
    }
}
