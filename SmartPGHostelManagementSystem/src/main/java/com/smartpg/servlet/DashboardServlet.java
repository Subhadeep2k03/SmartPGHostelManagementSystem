package com.smartpg.servlet;


import java.io.IOException;

import com.smartpg.dao.DashboardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        DashboardDAO dao = new DashboardDAO();


        int students = 0;
        int rooms = 0;
        int payments = 0;
        int complaints = 0;
        int visitors = 0;
        int notices = 0;


        try {


            students = dao.getTotalStudents();

            rooms = dao.getTotalRooms();

            payments = dao.getTotalPayments();

            complaints = dao.getTotalComplaints();

            visitors = dao.getTotalVisitors();

            notices = dao.getTotalNotices();



        } catch(Exception e) {

            e.printStackTrace();

        }



        request.setAttribute("students", students);

        request.setAttribute("rooms", rooms);

        request.setAttribute("payments", payments);

        request.setAttribute("complaints", complaints);

        request.setAttribute("totalVisitors", visitors);

        request.setAttribute("totalNotices", notices);



        request.getRequestDispatcher("adminDashboard.jsp")
               .forward(request, response);


    }


}