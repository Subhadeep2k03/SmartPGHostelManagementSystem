package com.smartpg.servlet;

import java.io.IOException;
import java.util.List;

import com.smartpg.dao.StudentDAO;
import com.smartpg.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        StudentDAO dao = new StudentDAO();


        List<Student> students = dao.getAllStudents();


        request.setAttribute("students", students);


        request.getRequestDispatcher("/student/viewStudents.jsp")
        .forward(request, response);

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = request.getParameter("action");


        StudentDAO dao = new StudentDAO();



        // UPDATE STUDENT

        if("update".equals(action)) {


            Student student = new Student();



            student.setStudentId(
                    Integer.parseInt(request.getParameter("studentId")));



            student.setFirstName(
                    request.getParameter("firstName"));



            student.setLastName(
                    request.getParameter("lastName"));



            student.setPhone(
                    request.getParameter("phone"));



            student.setEmail(
                    request.getParameter("email"));



            student.setAddress(
                    request.getParameter("address"));



            student.setStatus(
                    request.getParameter("status"));



            dao.updateStudent(student);



            request.getSession().setAttribute(
                    "message",
                    "Student Updated Successfully"
            );


        }



        response.sendRedirect(request.getContextPath() + "/StudentServlet");

    }

}