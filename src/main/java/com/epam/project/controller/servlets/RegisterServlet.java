package com.epam.project.controller.servlets;

import com.epam.project.model.entities.Role;
import com.epam.project.model.dao.UserDAO;
import com.epam.project.model.entities.User;
import com.epam.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/join")
public class RegisterServlet extends HttpServlet {
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
        System.out.println(lastName);
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(Role.USER, username, password, email, firstName, lastName);
        UserDAO userDAO = new UserDAO();
        if (userDAO.create(user)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            req.setAttribute("errorMsg", "Username is already taken!");
            req.getRequestDispatcher("/views/join.jsp").forward(req, resp);
        }
    }
}
