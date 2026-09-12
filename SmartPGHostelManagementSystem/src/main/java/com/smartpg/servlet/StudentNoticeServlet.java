package com.smartpg.servlet;

import java.io.IOException;
import java.util.List;

import com.smartpg.dao.NoticeDAO;
import com.smartpg.model.Notice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Notices are shared PG-wide information - every logged-in student sees
 * the same list (reuses the existing NoticeDAO.getAllNotices() as-is).
 */
@WebServlet("/student/StudentNoticeServlet")
public class StudentNoticeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NoticeDAO dao = new NoticeDAO();

        List<Notice> notices = dao.getAllNotices();

        request.setAttribute("notices", notices);

        request.getRequestDispatcher("/student/studentNotices.jsp")
               .forward(request, response);
    }
}
