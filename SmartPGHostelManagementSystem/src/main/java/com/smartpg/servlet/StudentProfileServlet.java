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
 * Read-only view of the logged-in student's own profile. studentId is
 * taken from the session only - there is no id/studentId request
 * parameter anywhere in this servlet, so there is nothing for another
 * student to manipulate to view someone else's profile.
 */
@WebServlet("/student/StudentProfileServlet")
public class StudentProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Integer studentId = (Integer) session.getAttribute("studentId");

        StudentDAO dao = new StudentDAO();

        Student student = dao.getStudentById(studentId);

        request.setAttribute("student", student);

        request.getRequestDispatcher("/student/studentProfile.jsp")
               .forward(request, response);
    }
}
