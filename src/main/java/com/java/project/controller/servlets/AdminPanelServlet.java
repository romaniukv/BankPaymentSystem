package com.java.project.controller.servlets;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.domain.CreditAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminPanel")
public class AdminPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreditAccountDAO creditAccountDAO = new CreditAccountDAO();
        List<CreditAccount> newCreditAccounts = creditAccountDAO.selectNewAccounts();
        req.setAttribute("newCreditAccounts", newCreditAccounts);
        req.getRequestDispatcher("/views/adminPanel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
