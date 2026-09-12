package com.smartpg.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.smartpg.dao.ComplaintDAO;
import com.smartpg.model.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Lets a student view their own complaints and submit a new one.
 * studentId is always taken from the session - it is intentionally never
 * read from a request parameter, so a student cannot file (or view) a
 * complaint under another student's id by editing a form/URL value.
 * Admin remains responsible for updating/resolving complaints
 * (see the existing ComplaintServlet, which is unchanged).
 */
@WebServlet("/student/StudentComplaintServlet")
public class StudentComplaintServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Integer studentId = (Integer) session.getAttribute("studentId");

        ComplaintDAO dao = new ComplaintDAO();

        List<Complaint> complaints = dao.getComplaintsByStudentId(studentId);

        request.setAttribute("complaints", complaints);

        request.getRequestDispatcher("/student/studentComplaints.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Integer studentId = (Integer) session.getAttribute("studentId");

        String roomIdParam = request.getParameter("roomId");
        String description = request.getParameter("description");

        if (roomIdParam == null || roomIdParam.trim().isEmpty()
                || description == null || description.trim().isEmpty()) {

            session.setAttribute("message", "Please fill in all fields.");
            response.sendRedirect(request.getContextPath() + "/student/StudentComplaintServlet");
            return;
        }

        Complaint complaint = new Complaint();

        complaint.setStudentId(studentId);
        complaint.setRoomId(Integer.parseInt(roomIdParam));
        complaint.setComplaintDate(LocalDate.now().toString());
        complaint.setDescription(description);
        complaint.setStatus("Pending");

        ComplaintDAO dao = new ComplaintDAO();

        dao.insertComplaint(complaint);

        session.setAttribute("message", "Complaint submitted successfully.");

        response.sendRedirect(request.getContextPath() + "/student/StudentComplaintServlet");
    }
}
