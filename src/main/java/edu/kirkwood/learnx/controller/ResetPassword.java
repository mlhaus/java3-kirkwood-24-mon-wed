package edu.kirkwood.learnx.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import edu.kirkwood.learnx.data.UserDAO;

@WebServlet("/reset-password")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Reset your password");
        req.getRequestDispatcher("WEB-INF/learnx/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("inputEmail1");
        Map<String, String> results = new HashMap<>();
        results.put("email", email);
        if(email != null && !email.equals("")) {
            UserDAO.passwordReset(email, req);
            results.put("passwordResetMsg", "If there is an account with the email entered, we will send a password reset link");
        }
        
        
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Reset your password");
        req.getRequestDispatcher("WEB-INF/learnx/reset-password.jsp").forward(req, resp);
        
    }
}
