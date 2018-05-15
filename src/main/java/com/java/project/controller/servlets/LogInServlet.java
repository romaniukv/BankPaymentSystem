package com.java.project.controller.servlets;

import com.java.project.model.dao.UserDAO;
import com.java.project.model.entities.User;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AppUtils.getLoginedUser(req.getSession()) != null) {
            req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            req.setAttribute("errorMsg", "Wrong username and(or) password! Try again.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }

        req.getSession().setAttribute("user", user);

        try {
            System.out.println(req.getParameter("redirectId"));
            int redirectId = Integer.parseInt(req.getParameter("redirectId"));
            System.out.println(redirectId);
            String requestUri = AppUtils.getRedirectAfterLoginUrl(redirectId);
            if (requestUri != null) {
                resp.sendRedirect(requestUri);
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
}