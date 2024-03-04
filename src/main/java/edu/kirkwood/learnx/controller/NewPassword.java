package edu.kirkwood.learnx.controller;

import edu.kirkwood.learnx.data.UserDAO;
import edu.kirkwood.learnx.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/new-password")
public class NewPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(token != null) {
            String email = UserDAO.getPasswordReset(token);
            HttpSession session = req.getSession();
            session.setAttribute("tempEmail", email);
        } else {
            // Send user to homepage if the token doesn't exist
            resp.sendRedirect("learnx");
            return;
        }
        req.setAttribute("pageTitle", "New Password");
        req.getRequestDispatcher("WEB-INF/learnx/new-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String)session.getAttribute("tempEmail");
        if(email != null) {
            System.out.println(email);
        }
        req.setAttribute("pageTitle", "New Password");
        req.getRequestDispatcher("WEB-INF/learnx/new-password.jsp").forward(req, resp);
    }
}

