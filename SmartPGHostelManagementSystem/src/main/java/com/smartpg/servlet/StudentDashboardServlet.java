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

@WebServlet("/student/StudentDashboardServlet")
public class StudentDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // studentId always comes from the session, never from the request -
        // this servlet only ever shows the logged-in student's own data.
        Integer studentId = (Integer) session.getAttribute("studentId");

        StudentDAO dao = new StudentDAO();

        Student student = dao.getStudentById(studentId);

        request.setAttribute("student", student);

        request.getRequestDispatcher("/student/studentDashboard.jsp")
               .forward(request, response);
    }
}
