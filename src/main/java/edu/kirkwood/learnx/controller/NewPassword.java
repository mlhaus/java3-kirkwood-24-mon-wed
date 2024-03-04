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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/new-password")
public class NewPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(token != null) {
            String email = UserDAO.getPasswordReset(token);
            if(!email.equals("")) {
                HttpSession session = req.getSession();
                session.setAttribute("tempEmail", email);
            } else {
                resp.sendRedirect("learnx");
                return;
            }
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
            String password1 = req.getParameter("inputPassword1");
            String password2 = req.getParameter("inputPassword2");
            Map<String, String> results = new HashMap<>();
            results.put("password1", password1);
            results.put("password2", password2);
            User userFromDatabase = UserDAO.get(email);
            try {
                userFromDatabase.setPassword(password1.toCharArray());
            } catch(IllegalArgumentException e) {
                results.put("password1Error", e.getMessage());
            }
            if(!password2.equals(password1)) {
                results.put("password2Error", "Passwords must match");
            }

            if(!results.containsKey("password1Error") &&
                    !results.containsKey("password2Error")
            ) {
                UserDAO.updatePassword(userFromDatabase);
                session.removeAttribute("tempEmail");
                userFromDatabase.setPassword(null);
                session.setAttribute("activeUser", userFromDatabase);
                session.setAttribute("flashMessageSuccess", "Your password has been updated");
                resp.sendRedirect("learnx");
                return;
            }
            
            req.setAttribute("results", results);
            req.setAttribute("pageTitle", "New Password");
            req.getRequestDispatcher("WEB-INF/learnx/new-password.jsp").forward(req, resp);
        }
    }
}

