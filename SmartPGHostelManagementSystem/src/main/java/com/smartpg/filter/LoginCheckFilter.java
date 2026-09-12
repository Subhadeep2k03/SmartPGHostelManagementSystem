package com.smartpg.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter("/*")
public class LoginCheckFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse res = (HttpServletResponse) response;


        String path = req.getRequestURI();


        // Public resources - reachable with no session at all.
        // (Admin login/static assets were already public; the four
        // student-facing entries below are the new additions so a student
        // can reach the registration/login pages before they have a session.)
        if(path.endsWith("login.jsp")
                || path.endsWith("LoginServlet")
                || path.contains("/css/")
                || path.contains("/js/")
                || path.contains("/images/")
                || path.endsWith("studentLogin.jsp")
                || path.endsWith("StudentLoginServlet")
                || path.endsWith("studentRegister.jsp")
                || path.endsWith("StudentRegisterServlet")) {


            chain.doFilter(request, response);

            return;
        }


        HttpSession session = req.getSession(false);

        String role = (session != null) ? (String) session.getAttribute("role") : null;

        // Everything under /student/ is the Student portal and requires an
        // authenticated STUDENT session; everything else is the existing
        // Admin area and requires an authenticated ADMIN session. This is
        // enforced here, server-side, on every request - a Student session
        // is never granted access to Admin URLs (and vice versa) no matter
        // what a JSP does or doesn't show/link to.
        boolean isStudentArea = path.contains("/student/");

        if (isStudentArea) {

            if (session != null && "STUDENT".equals(role)) {

                chain.doFilter(request, response);

            } else {

                res.sendRedirect(
                        req.getContextPath() + "/student/studentLogin.jsp"
                );
            }

        } else {

            if (session != null && "ADMIN".equals(role)) {

                chain.doFilter(request, response);

            } else {

                res.sendRedirect(
                        req.getContextPath() + "/login.jsp"
                );
            }
        }


    }

}