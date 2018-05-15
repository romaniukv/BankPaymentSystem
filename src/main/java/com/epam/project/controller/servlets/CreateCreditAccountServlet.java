package com.epam.project.controller.servlets;

import com.epam.project.model.dao.BankConfigDAO;
import com.epam.project.model.dao.CreditAccountDAO;
import com.epam.project.model.dao.UserDAO;
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
        User user = AppUtils.getLoginedUser(req.getSession());
        CreditAccount creditAccount = new CreditAccountDAO().selectByUserId(user.getId());
        if (creditAccount != null) {
            req.setAttribute("errorMessage", "You're already have credit account in our system!");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
        else {
            req.getSession().setAttribute("creditLimits", new BankConfigDAO().selectCreditLimits());
            req.getRequestDispatcher("/views/createCreditAccount.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditLimit"));
        BigDecimal creditRate = new BankConfigDAO().selectCreditLimits().get(creditLimit);
        User user = AppUtils.getLoginedUser(req.getSession());
        long accountNumber = new BankConfigDAO().getNewAccountNumber();

        CreditAccountDAO creditAccountDAO = new CreditAccountDAO();
        boolean flag = creditAccountDAO.create(new CreditAccount(accountNumber, user.getId(), creditLimit, creditRate));

        if (flag) {
            req.setAttribute("successMessage", "Application for opening a credit account was successfully sent");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "There was an error opening the credit account. Try again.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
