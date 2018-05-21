package com.java.project.controller.servlets.deposit;

import com.java.project.services.BankConfigService;
import com.java.project.model.domain.DepositAccount;
import com.java.project.model.domain.User;
import com.java.project.services.DepositAccountService;
import com.java.project.services.impl.DepositAccountServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


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
                req.setAttribute("account", new BankConfigService().findDepositInCatalog(depositId));
                req.getRequestDispatcher("/views/deposit/createDepositAccount.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "");
                req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();
        long accountNumber = new BankConfigService().getNewAccountNumber();

        int id = Integer.valueOf(req.getParameter("depositId"));
        DepositAccount depositAccount = new BankConfigService().findDepositInCatalog(id);
        depositAccount.setAmount(amount);
        depositAccount.setBalance(amount);
        depositAccount.setUserId(userId);
        depositAccount.setNumber(accountNumber);
        depositAccount.calculateExpirationDate();

        DepositAccountService depositAccountService = new DepositAccountServiceImpl();
        boolean flag = depositAccountService.create(depositAccount);

        if (flag) {
            req.setAttribute("successMessage", "");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "There was an error opening the deposit account. Try again.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
