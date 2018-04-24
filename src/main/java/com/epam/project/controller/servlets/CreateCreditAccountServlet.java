package com.epam.project.controller.servlets;

import com.epam.project.dao.BankConfigDAO;
import com.epam.project.dao.CreditAccountDAO;
import com.epam.project.model.entities.Account;
import com.epam.project.model.entities.CreditAccount;
import com.epam.project.model.entities.User;
import com.epam.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/createCreditAccount")
public class CreateCreditAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("creditLimits", BankConfigDAO.selectCreditLimits());
        req.getRequestDispatcher("/views/createCreditAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer creditLimit = Integer.valueOf(req.getParameter("creditLimit"));
        Map<Integer, Double> creditLimits = (Map<Integer, Double>) req.getSession().getAttribute("creditLimits");
        CreditAccountDAO creditAccountDAO = new CreditAccountDAO();
        creditAccountDAO.create(new CreditAccount(creditLimit, creditLimits.get(creditLimit)));
    }
}
