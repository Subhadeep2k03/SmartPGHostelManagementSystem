package com.smartpg.servlet;

import java.io.IOException;

import com.smartpg.dao.StudentDAO;
import com.smartpg.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = new Student();

        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setGender(request.getParameter("gender"));
        student.setDob(request.getParameter("dob"));
        student.setPhone(request.getParameter("phone"));
        student.setEmail(request.getParameter("email"));
        student.setAddress(request.getParameter("address"));
        student.setGuardianName(request.getParameter("guardianName"));
        student.setGuardianPhone(request.getParameter("guardianPhone"));
        student.setJoinDate(request.getParameter("joinDate"));
        student.setStatus(request.getParameter("status"));

        StudentDAO dao = new StudentDAO();

        dao.addStudent(student);
        request.getSession().setAttribute(
        	    "message",
        	    "Student Added Successfully"
        	);
        // Redirect back to StudentServlet
        response.sendRedirect(request.getContextPath() + "/StudentServlet");
    }
}