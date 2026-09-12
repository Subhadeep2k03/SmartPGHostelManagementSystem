package com.smartpg.servlet;

import java.io.IOException;

import com.smartpg.dao.StudentDAO;
import com.smartpg.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Student login - kept entirely separate from the existing Admin
 * LoginServlet/AdminDAO so the Admin login flow is untouched.
 */
@WebServlet("/student/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        StudentDAO dao = new StudentDAO();

        Student student = dao.loginStudent(email, password);

        if (student != null) {

            HttpSession session = request.getSession();

            // Session-based identity: every student-facing page/servlet
            // must read studentId from here, never from a request
            // parameter - this is what prevents one student from viewing
            // another student's data by editing a URL/form field.
            session.setAttribute("studentId", student.getStudentId());
            session.setAttribute("studentName", student.getFirstName());
            session.setAttribute("role", "STUDENT");

            response.sendRedirect(request.getContextPath() + "/student/StudentDashboardServlet");

        } else {

            request.setAttribute("error", "Invalid email or password.");

            request.getRequestDispatcher("/student/studentLogin.jsp")
                   .forward(request, response);
        }
    }
}
