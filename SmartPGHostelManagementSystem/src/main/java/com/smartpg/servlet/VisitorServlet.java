package com.smartpg.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.smartpg.dao.VisitorDAO;
import com.smartpg.model.Visitor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/VisitorServlet")
public class VisitorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private VisitorDAO dao = new VisitorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

        case "list":

            List<Visitor> visitors = dao.getAllVisitors();

            request.setAttribute("visitors", visitors);

            request.getRequestDispatcher("/visitor/viewVisitor.jsp").forward(request, response);
            

            break;

        case "edit":

            int id = Integer.parseInt(request.getParameter("id"));

            Visitor visitor = dao.getVisitorById(id);

            request.setAttribute("visitor", visitor);

            request.getRequestDispatcher("/visitor/editVisitor.jsp")
                   .forward(request, response);

            break;

        case "delete":

            int deleteId = Integer.parseInt(request.getParameter("id"));

            dao.deleteVisitor(deleteId);

            request.getSession().setAttribute("message",
                    "Visitor deleted successfully.");

            response.sendRedirect("VisitorServlet?action=list");

            break;

        default:

            response.sendRedirect("VisitorServlet?action=list");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String visitorId = request.getParameter("visitorId");

        Visitor visitor = new Visitor();

        visitor.setStudentId(
                Integer.parseInt(request.getParameter("studentId")));

        visitor.setVisitorName(
                request.getParameter("visitorName"));

        visitor.setPhone(
                request.getParameter("phone"));

        visitor.setRelation(
                request.getParameter("relation"));

        visitor.setVisitDate(
                Date.valueOf(request.getParameter("visitDate")));

        String inTime = request.getParameter("inTime");

        if(inTime != null && !inTime.isEmpty()) {

            if(inTime.length() == 5) {
                inTime = inTime + ":00";
            }

            visitor.setInTime(Time.valueOf(inTime));

        }

        String outTime = request.getParameter("outTime");

        if(outTime != null && !outTime.isEmpty()) {

            if(outTime.length() == 5) {
                outTime = outTime + ":00";
            }

            visitor.setOutTime(Time.valueOf(outTime));

        }

        if (visitorId == null || visitorId.isEmpty()) {

            dao.addVisitor(visitor);

            request.getSession().setAttribute("message",
                    "Visitor added successfully.");

        } else {

            visitor.setVisitorId(Integer.parseInt(visitorId));

            dao.updateVisitor(visitor);

            request.getSession().setAttribute("message",
                    "Visitor updated successfully.");

        }

        response.sendRedirect("VisitorServlet?action=list");

    }

}