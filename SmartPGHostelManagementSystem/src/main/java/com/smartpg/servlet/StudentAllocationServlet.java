package com.smartpg.servlet;

import java.io.IOException;
import java.util.List;

import com.smartpg.dao.RoomAllocationDAO;
import com.smartpg.model.RoomAllocation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Shows the logged-in student's own room allocation(s) only.
 * studentId is taken from the session, never from a request parameter.
 */
@WebServlet("/student/StudentAllocationServlet")
public class StudentAllocationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Integer studentId = (Integer) session.getAttribute("studentId");

        RoomAllocationDAO dao = new RoomAllocationDAO();

        List<RoomAllocation> allocations = dao.getAllocationsByStudentId(studentId);

        request.setAttribute("allocations", allocations);

        request.getRequestDispatcher("/student/studentAllocation.jsp")
               .forward(request, response);
    }
}
