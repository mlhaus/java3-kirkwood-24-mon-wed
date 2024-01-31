package edu.kirkwood.shared;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/errorHandler")
public class ErrorHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statusCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) + "";
        String exceptionType = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE) + "";
        String errorMessage = req.getAttribute(RequestDispatcher.ERROR_MESSAGE) + "";
        String servletName = req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME) + "";

        String myErrorMsg = "Error code: " + statusCode;
        if(exceptionType != null) {
            myErrorMsg += "\nException: " + exceptionType;
        }
        if(errorMessage != null) {
            myErrorMsg += "\nMessage: " + errorMessage;
        }
        System.err.println(myErrorMsg);

        if(statusCode.equals("500")) {
            String url = getURL(req);
            myErrorMsg = "<html><body><ul>";
            myErrorMsg += "<li>Error code: " + statusCode + "</li>";
            myErrorMsg += "<li>Servlet: " + servletName + "</li>";
            myErrorMsg += "<li>URL: <a href=\"" + url + "\">" + url + "</a></li>";
            myErrorMsg += "<li>Exception: " + exceptionType + "</li>";
            myErrorMsg += "<li>Message: " + errorMessage + "</li>";
            myErrorMsg += "</ul></body></html>";
            CommunicationService.sendEmail(Dotenv.load().get("ADMIN_EMAIL"), "Server Error", myErrorMsg);
        }


        req.setAttribute("pageTitle", "Error");
        req.getRequestDispatcher("WEB-INF/shared/error.jsp").forward(req, resp);
    }
    
    // https://stackoverflow.com/questions/2222238/httpservletrequest-to-complete-url/
    public static String getURL(HttpServletRequest req) {

        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp
        String servletPath = req.getServletPath();   // /servlet/MyServlet
        String pathInfo = req.getPathInfo();         // /a/b;c=123
        String queryString = req.getQueryString();          // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
