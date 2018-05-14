package com.epam.project.controller.servlets;

import com.epam.project.model.dao.BankConfigDAO;
import com.epam.project.model.dao.CreditAccountDAO;
import com.epam.project.model.entities.CreditAccount;
import com.epam.project.model.entities.User;
import com.epam.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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
        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditLimit"));
        Map<BigDecimal, BigDecimal> creditLimits = (Map<BigDecimal, BigDecimal>) req.getSession().getAttribute("creditLimits");

        User user = AppUtils.getLoginedUser(req.getSession());
        long accountNumber = BankConfigDAO.getNewAccountNumber();

        CreditAccountDAO creditAccountDAO = new CreditAccountDAO();
        boolean flag = creditAccountDAO.create(new CreditAccount(accountNumber, user.getId(), creditLimit, creditLimits.get(creditLimit)));

        if (flag) {
            req.setAttribute("successMessage", "Application for opening a credit account was successfully sent");
            resp.sendRedirect("/successMessage");
        }
        else {
            req.setAttribute("errorMessage", "There was an error opening the credit account. Try again.");
            resp.sendRedirect("/errorMessage");
        }
    }
}
