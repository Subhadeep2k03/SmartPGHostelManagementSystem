package com.smartpg.servlet;


import java.io.IOException;

import com.smartpg.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");


        AdminDAO dao = new AdminDAO();


        boolean result = dao.login(username, password);



        if(result) {


            HttpSession session = request.getSession();

            session.setAttribute("username", username);
            session.setAttribute("role", "ADMIN");


            response.sendRedirect("DashboardServlet");


        } else {


            request.setAttribute("error", "Invalid username or password");


            request.getRequestDispatcher("/login.jsp")
                   .forward(request, response);

        }


    }

}