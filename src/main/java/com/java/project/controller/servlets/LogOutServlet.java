package com.java.project.controller.servlets;

import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logOut")
public class LogOutServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LogOutServlet.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AppUtils.getLoginedUser(req.getSession());
        req.getSession().removeAttribute("user");
        logger.info("User " + user.getUsername() + " successfully logged out.");
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
