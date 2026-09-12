package com.smartpg.servlet;

import java.io.IOException;

import com.smartpg.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int studentId =
                Integer.parseInt(request.getParameter("id"));

        StudentDAO dao = new StudentDAO();

        dao.deleteStudent(studentId);

        // Success message
        request.getSession().setAttribute(
                "message",
                "Student Deleted Successfully"
        );

        // Back to Manage Student
        response.sendRedirect("StudentServlet");
    }
}
