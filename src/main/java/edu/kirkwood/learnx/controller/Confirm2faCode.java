package edu.kirkwood.learnx.controller;

import edu.kirkwood.learnx.data.UserDAO;
import edu.kirkwood.learnx.model.User;
import edu.kirkwood.shared.CommunicationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/confirm")
public class Confirm2faCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resend = req.getParameter("resend");
        if(resend != null) {
            HttpSession session = req.getSession();
            String codeFromSession = (String)session.getAttribute("code");
            if(codeFromSession != null && !codeFromSession.equals("")) {
                String email = (String) session.getAttribute("email");
                CommunicationService.sendNewUserEmail(email, codeFromSession);
                req.setAttribute("emailSent", "A new email was sent with your access code");
            }
        }
        req.setAttribute("pageTitle", "Confirm Signup Code");
        req.getRequestDispatcher("WEB-INF/learnx/2fa-confirm.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("inputCode");
        Map<String, String> results = new HashMap<>();
        results.put("code", code);

        // Check if the code entered matches the one in the session.
        HttpSession session = req.getSession();
        String codeFromSession = (String)session.getAttribute("code");
        if(!code.equals(codeFromSession)) {
            results.put("codeFail", "That code is not correct");
        } else {
            String email = (String)session.getAttribute("email");
            User userFromDatabase = UserDAO.get(email);
            userFromDatabase.setStatus("active");
            userFromDatabase.setPrivileges("student");
            // To Do: Get an instant representing UTC 0
            userFromDatabase.setLast_logged_in(Instant.now().atOffset( ZoneOffset.UTC ).toInstant());
            UserDAO.update(userFromDatabase);
            userFromDatabase.setPassword(null);
            session.removeAttribute("code");
            session.removeAttribute("email");
            session.setAttribute("activeUser", userFromDatabase);
            session.setAttribute("flashMessageSuccess", "Welcome New User!");
            resp.sendRedirect("learnx");
            return;
        }

        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Confirm Signup Code");
        req.getRequestDispatcher("WEB-INF/learnx/2fa-confirm.jsp").forward(req,resp);

    }
}
