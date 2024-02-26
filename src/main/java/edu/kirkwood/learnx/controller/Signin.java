package edu.kirkwood.learnx.controller;

import edu.kirkwood.learnx.data.UserDAO;
import edu.kirkwood.learnx.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signin")
public class Signin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Sign in");
        req.getRequestDispatcher("WEB-INF/learnx/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("inputEmail1");
        String password1 = req.getParameter("inputPassword1");
        String[] remember = req.getParameterValues("checkbox-1");
        Map<String, String> results = new HashMap<>();
        results.put("email", email);
        results.put("password1", password1);
        if(remember != null && remember[0].equals("yes")) {
            results.put("remember", "yes");
        }
        User userFromDatabase = UserDAO.get(email);
        if(userFromDatabase == null) {
            // Email entered is not correct
            results.put("loginError", "The email or password you entered is not correct");
        } else {
            String hashedPassword = String.valueOf(userFromDatabase.getPassword());
            if (!BCrypt.checkpw(password1, hashedPassword)) {
                // Password entered is not correct
                results.put("loginError", "The email or password you entered is not correct");
            } else {
                if(userFromDatabase.getStatus() == null || !userFromDatabase.getStatus().equals("active")) {
                    results.put("loginError", "Your account is not active. Please contact support for help.");
                }  else {
                    // Email and password are correct, and it's an active user
                    // To Do: Get an instant representing UTC 0
                    userFromDatabase.setLast_logged_in(Instant.now().atOffset(ZoneOffset.UTC).toInstant());
                    UserDAO.update(userFromDatabase);
                    userFromDatabase.setPassword(null);
                    HttpSession session = req.getSession();
                    session.setAttribute("activeUser", userFromDatabase);
                    session.setAttribute("flashMessageSuccess", "Welcome Back!");
                    if(remember != null && remember[0].equals("yes")) {
                        session.setMaxInactiveInterval(7 * 24 * 60 * 60); // 7 days
                    }
                    resp.sendRedirect("learnx");
                    return;
                }
            }
        }
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Sign in");
        req.getRequestDispatcher("WEB-INF/learnx/signin.jsp").forward(req, resp);
    }
}
