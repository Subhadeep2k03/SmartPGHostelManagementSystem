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


@WebServlet("/RoomAllocationServlet")
public class RoomAllocationServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String action = request.getParameter("action");


        RoomAllocationDAO dao = new RoomAllocationDAO();




        // DELETE ALLOCATION

        if ("delete".equals(action)) {



            int id = Integer.parseInt(
                    request.getParameter("id"));



            dao.deleteRoomAllocation(id);



            request.getSession().setAttribute(
                    "message",
                    "Room Allocation Deleted Successfully"
            );



            response.sendRedirect("RoomAllocationServlet");

            return;

        }





        // VIEW ALLOCATIONS

        List<RoomAllocation> allocations =
                dao.getAllRoomAllocations();



        request.setAttribute(
                "allocations",
                allocations);



        request.getRequestDispatcher(
                "/roomallocation/viewAllocations.jsp")
                .forward(request, response);


    }







    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        String action = request.getParameter("action");


        RoomAllocationDAO dao = new RoomAllocationDAO();





        // ADD ALLOCATION

        if ("add".equals(action)) {



            RoomAllocation allocation = new RoomAllocation();



            allocation.setStudentId(
                    Integer.parseInt(request.getParameter("studentId")));




            allocation.setRoomId(
                    Integer.parseInt(request.getParameter("roomId")));




            allocation.setAllocationDate(
                    request.getParameter("allocationDate"));




            allocation.setEndDate(
                    request.getParameter("endDate"));




            allocation.setStatus(
                    request.getParameter("status"));




            dao.addRoomAllocation(allocation);



            request.getSession().setAttribute(
                    "message",
                    "Room Allocation Added Successfully"
            );



        }






        // UPDATE ALLOCATION

        else if ("update".equals(action)) {



            RoomAllocation allocation = new RoomAllocation();



            allocation.setAllocationId(
                    Integer.parseInt(request.getParameter("allocationId")));




            allocation.setEndDate(
                    request.getParameter("endDate"));




            allocation.setStatus(
                    request.getParameter("status"));




            dao.updateRoomAllocation(allocation);




            request.getSession().setAttribute(
                    "message",
                    "Room Allocation Updated Successfully"
            );



        }



        response.sendRedirect("RoomAllocationServlet");


    }


}