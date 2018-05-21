package com.java.project.controller.servlets.credit;

import com.java.project.services.BankConfigService;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
import com.java.project.services.CreditAccountService;
import com.java.project.services.impl.CreditAccountServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/createCreditAccount")
public class CreateCreditAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AppUtils.getLoginedUser(req.getSession());
        CreditAccount creditAccount = new CreditAccountServiceImpl().selectByUserId(user.getId());
        if (creditAccount != null && creditAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("errorMessage", "You're already have credit account in our system!");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
        else {
            req.getSession().setAttribute("creditLimits", new BankConfigService().selectCreditLimits());
            req.getRequestDispatcher("/views/credit/createCreditAccount.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditLimit"));
        BigDecimal creditRate = new BankConfigService().selectCreditLimits().get(creditLimit);
        User user = AppUtils.getLoginedUser(req.getSession());
        long accountNumber = new BankConfigService().getNewAccountNumber();

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = new CreditAccount(accountNumber, user.getId(), creditLimit, creditRate);
        creditAccount.calculateExpirationDate();
        creditAccount.setStatus(AccountStatus.UNDER_CONSIDERATION);
        boolean flag = creditAccountService.create(creditAccount);

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
