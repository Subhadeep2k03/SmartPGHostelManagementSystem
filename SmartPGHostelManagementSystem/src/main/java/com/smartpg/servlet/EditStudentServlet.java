package com.smartpg.servlet;

import java.io.IOException;

import com.smartpg.dao.StudentDAO;
import com.smartpg.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Edit ID = " + id);

        StudentDAO dao = new StudentDAO();

        Student student = dao.getStudentById(id);
        System.out.println("Student = " + student);
        request.setAttribute("student", student);

        request.getRequestDispatcher("student/editStudent.jsp")
               .forward(request, response);
    }
}