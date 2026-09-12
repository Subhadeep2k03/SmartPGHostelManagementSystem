package com.smartpg.servlet;


import java.io.IOException;

import com.smartpg.dao.RoomDAO;
import com.smartpg.model.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/EditRoomServlet")
public class EditRoomServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println("Edit Room Servlet Called");

        System.out.println("ID = " + request.getParameter("id"));


        int id = Integer.parseInt(request.getParameter("id"));


        RoomDAO dao = new RoomDAO();


        Room room = dao.getRoomById(id);


        request.setAttribute("room", room);


        request.getRequestDispatcher("room/editRoom.jsp")
               .forward(request, response);


    }
}