package com.smartpg.servlet;

import java.io.IOException;
import java.time.LocalDate;

import com.smartpg.dao.StudentDAO;
import com.smartpg.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Student self-registration. Public (no session required) - see
 * LoginCheckFilter, which allow-lists this servlet and studentRegister.jsp.
 */
@WebServlet("/student/StudentRegisterServlet")
public class StudentRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/student/studentRegister.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String guardianName = request.getParameter("guardianName");
        String guardianPhone = request.getParameter("guardianPhone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        StudentDAO dao = new StudentDAO();

        // --- Server-side validation (never trust the client alone) ---

        if (isBlank(firstName) || isBlank(gender) || isBlank(dob) || isBlank(phone)
                || isBlank(email) || isBlank(guardianName) || isBlank(guardianPhone)
                || isBlank(password)) {

            showError(request, response, "Please fill in all required fields.");
            return;
        }

        if (password.length() < 8) {
            showError(request, response, "Password must be at least 8 characters long.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError(request, response, "Passwords do not match.");
            return;
        }

        if (dao.isEmailTaken(email)) {
            showError(request, response, "An account with this email already exists.");
            return;
        }

        if (dao.isPhoneTaken(phone)) {
            showError(request, response, "An account with this phone number already exists.");
            return;
        }

        Student student = new Student();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGender(gender);
        student.setDob(dob);
        student.setPhone(phone);
        student.setEmail(email);
        student.setAddress(address);
        student.setGuardianName(guardianName);
        student.setGuardianPhone(guardianPhone);
        // join date and status are always set server-side, never taken
        // from the registration form.
        student.setJoinDate(LocalDate.now().toString());

        boolean created = dao.registerStudent(student, password);

        if (created) {

            request.getSession().setAttribute(
                    "message",
                    "Registration successful. Please log in."
            );

            response.sendRedirect(request.getContextPath() + "/student/studentLogin.jsp");

        } else {

            showError(request, response, "Registration failed. Please try again.");
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private void showError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {

        request.setAttribute("error", message);

        request.getRequestDispatcher("/student/studentRegister.jsp")
               .forward(request, response);
    }
}
