package com.java.project.controller.servlets;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;
import com.java.project.utils.PasswordUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for login.
 * Searches user by username and password and if there is no such shows error message.
 * If there is such user sets it as session attribute and then redirects by redirect id or
 * (if it is no redirect id in request) to profile page.
 */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LogInServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AppUtils.getLoginedUser(req.getSession()) != null) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = ServiceFactory.getUserService().findUserByUsername(username);

        if (user == null || !PasswordUtils.checkPassword(password, user.getPassword())) {
            logger.warn("User " + username + " failed to log in.");
            req.setAttribute("errorMsg", LocalizationUtils.WRONG_USERNAME_PASS);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
        else {
            req.getSession().setAttribute("user", user);
            logger.info("User " + username + " successfully logged in.");
            if (user.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/adminPanel");
            }
            else {
                try {
                    int redirectId = Integer.parseInt(req.getParameter("redirectId"));
                    String requestUri = AppUtils.getRedirectUrl(redirectId);
                    if (requestUri != null) {
                        resp.sendRedirect(requestUri);
                    }
                } catch (Exception e) {
                    resp.sendRedirect(req.getContextPath() + "/profile");
                }
            }
        }
    }
}
