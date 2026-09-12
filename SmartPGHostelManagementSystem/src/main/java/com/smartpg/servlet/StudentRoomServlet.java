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

/**
 * Shows available rooms - this is shared, non-private information every
 * logged-in student may see (unlike payments/allocations/visitors/
 * complaints, which are scoped to the session's studentId).
 */
@WebServlet("/student/StudentRoomServlet")
public class StudentRoomServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RoomDAO dao = new RoomDAO();

        List<Room> rooms = dao.getAvailableRooms();

        request.setAttribute("rooms", rooms);

        request.getRequestDispatcher("/student/studentRooms.jsp")
               .forward(request, response);
    }
}
