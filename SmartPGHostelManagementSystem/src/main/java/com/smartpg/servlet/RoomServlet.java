package com.smartpg.servlet;


import java.io.IOException;
import java.util.List;

import com.smartpg.dao.RoomDAO;
import com.smartpg.model.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RoomDAO dao = new RoomDAO();


        List<Room> rooms = dao.getAllRooms();


        request.setAttribute("rooms", rooms);


        request.getRequestDispatcher("room/viewRooms.jsp")
               .forward(request, response);

    }






    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String action = request.getParameter("action");


        RoomDAO dao = new RoomDAO();




        // ADD ROOM

        if("add".equals(action)) {



            Room room = new Room();



            room.setRoomNumber(
                    request.getParameter("roomNumber"));



            room.setFloor(
                    Integer.parseInt(request.getParameter("floor")));



            room.setRoomType(
                    request.getParameter("roomType"));



            room.setCapacity(
                    Integer.parseInt(request.getParameter("capacity")));



            room.setRent(
                    Integer.parseInt(request.getParameter("rent")));



            room.setStatus(
                    request.getParameter("status"));



            dao.addRoom(room);



            request.getSession().setAttribute(
                    "message",
                    "Room Added Successfully"
            );


        }






        // UPDATE ROOM

        else if("update".equals(action)) {



            Room room = new Room();



            room.setRoomId(
                    Integer.parseInt(request.getParameter("roomId")));




            room.setRoomNumber(
                    request.getParameter("roomNumber"));



            room.setFloor(
                    Integer.parseInt(request.getParameter("floor")));




            room.setRoomType(
                    request.getParameter("roomType"));



            room.setCapacity(
                    Integer.parseInt(request.getParameter("capacity")));




            room.setRent(
                    Integer.parseInt(request.getParameter("rent")));




            room.setStatus(
                    request.getParameter("status"));




            dao.updateRoom(room);



            request.getSession().setAttribute(
                    "message",
                    "Room Updated Successfully"
            );


        }




        response.sendRedirect("RoomServlet");

    }

}