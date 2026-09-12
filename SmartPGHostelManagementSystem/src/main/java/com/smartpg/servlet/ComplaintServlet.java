package com.smartpg.servlet;

import java.io.IOException;
import java.util.List;

import com.smartpg.dao.ComplaintDAO;
import com.smartpg.model.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = request.getParameter("action");


        ComplaintDAO dao = new ComplaintDAO();



        // DELETE COMPLAINT

        if ("delete".equals(action)) {


            int complaintId = Integer.parseInt(
                    request.getParameter("id"));


            dao.deleteComplaint(complaintId);



            request.getSession().setAttribute(
                    "message",
                    "Complaint Deleted Successfully"
            );


            response.sendRedirect("ComplaintServlet");

            return;

        }



        // EDIT COMPLAINT (load existing data before showing the form)

        if ("edit".equals(action)) {

            int complaintId = Integer.parseInt(
                    request.getParameter("id"));

            Complaint complaint = dao.getComplaintById(complaintId);

            request.setAttribute("complaint", complaint);

            request.getRequestDispatcher("/complaint/editComplaint.jsp")
                   .forward(request, response);

            return;

        }




        // VIEW COMPLAINTS

        List<Complaint> complaints = dao.getAllComplaints();


        request.setAttribute("complaints", complaints);


        request.getRequestDispatcher("/complaint/viewComplaints.jsp")
               .forward(request, response);

    }






    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = request.getParameter("action");


        ComplaintDAO dao = new ComplaintDAO();




        // ADD COMPLAINT

        if ("add".equals(action)) {


            Complaint complaint = new Complaint();


            complaint.setStudentId(
                    Integer.parseInt(request.getParameter("studentId")));



            complaint.setRoomId(
                    Integer.parseInt(request.getParameter("roomId")));



            complaint.setComplaintDate(
                    request.getParameter("complaintDate"));



            complaint.setDescription(
                    request.getParameter("description"));



            complaint.setStatus(
                    request.getParameter("status"));



            dao.insertComplaint(complaint);



            request.getSession().setAttribute(
                    "message",
                    "Complaint Added Successfully"
            );


        }





        // UPDATE COMPLAINT

        else if ("update".equals(action)) {


            Complaint complaint = new Complaint();


            complaint.setComplaintId(
                    Integer.parseInt(request.getParameter("complaintId")));



            complaint.setStatus(
                    request.getParameter("status"));



            dao.updateComplaint(complaint);



            request.getSession().setAttribute(
                    "message",
                    "Complaint Updated Successfully"
            );


        }



        response.sendRedirect("ComplaintServlet");

    }

}