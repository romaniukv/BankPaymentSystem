package com.java.project.controller.servlets;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.Role;
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
 * Servlet for registration new user.
 * If user with given username is not registered encrypts password and
 * saved new user.
 */
@WebServlet("/join")
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AppUtils.getLoginedUser(req.getSession()) != null) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            req.getRequestDispatcher("/views/join.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(Role.USER, username, PasswordUtils.encryptPassword(password), email, firstName, lastName);

        if (ServiceFactory.getUserService().create(user)) {
            logger.info("User " + username + " successfully registered.");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            logger.error("User " + username + " failed to register.");
            req.setAttribute("errorMsg", LocalizationUtils.REGISTER_ERROR);
            req.getRequestDispatcher("/views/join.jsp").forward(req, resp);
        }
    }
}
