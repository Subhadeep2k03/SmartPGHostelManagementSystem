package com.smartpg.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.smartpg.dao.NoticeDAO;
import com.smartpg.model.Notice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private NoticeDAO dao = new NoticeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

        case "list":

            List<Notice> notices = dao.getAllNotices();

            request.setAttribute("notices", notices);

            request.getRequestDispatcher("/notice/viewNotice.jsp")
                   .forward(request, response);

            break;

        case "edit":

            int id = Integer.parseInt(request.getParameter("id"));

            Notice notice = dao.getNoticeById(id);

            request.setAttribute("notice", notice);

            request.getRequestDispatcher("/notice/editNotice.jsp")
                   .forward(request, response);

            break;

        case "delete":

            int deleteId = Integer.parseInt(request.getParameter("id"));

            dao.deleteNotice(deleteId);

            request.getSession().setAttribute("message", "Notice deleted successfully.");

            response.sendRedirect("NoticeServlet?action=list");

            break;

        default:

            response.sendRedirect("NoticeServlet?action=list");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String noticeId = request.getParameter("noticeId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date noticeDate = Date.valueOf(request.getParameter("noticeDate"));

        Notice notice = new Notice();

        notice.setTitle(title);
        notice.setDescription(description);
        notice.setNoticeDate(noticeDate);

        if (noticeId == null || noticeId.isEmpty()) {

            dao.addNotice(notice);

            request.getSession().setAttribute("message", "Notice added successfully.");

        } else {

            notice.setNoticeId(Integer.parseInt(noticeId));

            dao.updateNotice(notice);

            request.getSession().setAttribute("message", "Notice updated successfully.");

        }

        response.sendRedirect("NoticeServlet?action=list");
    }
}