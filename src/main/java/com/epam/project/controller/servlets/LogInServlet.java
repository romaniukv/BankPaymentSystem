package com.epam.project.controller.servlets;

import com.epam.project.dao.UserDAO;
import com.epam.project.model.entities.User;
import com.epam.project.utils.AppUtils;

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
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            req.setAttribute("errorMsg", "Wrong username and(or) password! Try again.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
