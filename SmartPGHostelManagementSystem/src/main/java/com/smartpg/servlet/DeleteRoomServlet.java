package com.smartpg.servlet;


import java.io.IOException;

import com.smartpg.dao.RoomDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/DeleteRoomServlet")
public class DeleteRoomServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        int roomId = Integer.parseInt(request.getParameter("id"));



        RoomDAO dao = new RoomDAO();



        dao.deleteRoom(roomId);



        request.getSession().setAttribute(
                "message",
                "Room Deleted Successfully"
        );



        response.sendRedirect("RoomServlet");


    }


}