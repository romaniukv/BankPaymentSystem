package com.java.project.controller.servlets;

import com.java.project.utils.LocalizationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Changes language of web application.
 * Gets language from request, sets it as session attribute.
 */
@WebServlet("/changeLanguage")
public class ChangeLanguageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language =  req.getParameter("language");

        LocalizationUtils.changeLanguage(language);
        req.getSession().setAttribute("language", language);

        resp.sendRedirect(req.getContextPath() + "/home");
    }

}
