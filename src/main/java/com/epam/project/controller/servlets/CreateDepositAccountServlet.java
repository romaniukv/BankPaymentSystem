package com.epam.project.controller.servlets;

import com.epam.project.model.dao.BankConfigDAO;
import com.epam.project.model.entities.User;
import com.epam.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/createDepositAccount")
public class CreateDepositAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AppUtils.getLoginedUser(req.getSession());
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        else {
            try {
                int depositId = Integer.valueOf(req.getParameter("id"));
                req.setAttribute("account", new BankConfigDAO().findDepositInCatalog(depositId));
                req.getRequestDispatcher("/views/createDepositAccount.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "");
                req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (true) {
            req.setAttribute("successMessage", "");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "There was an error opening the deposit account. Try again.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
