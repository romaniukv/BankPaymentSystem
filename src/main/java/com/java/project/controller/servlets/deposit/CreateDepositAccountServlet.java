package com.java.project.controller.servlets.deposit;

import com.java.project.factory.ServiceFactory;
import com.java.project.services.BankConfigService;
import com.java.project.model.domain.DepositAccount;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;

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
        int id = AppUtils.getIdFromRequest(req, resp);
        DepositAccount deposit = ServiceFactory.getBankConfigService().findDepositInCatalog(id);
        if (deposit != null) {
            req.setAttribute("account", deposit);
            req.getRequestDispatcher("/views/deposit/createDepositAccount.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", LocalizationUtils.CANT_CREATE_DEPOSIT);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();
        int id = AppUtils.getIdFromRequest(req, resp);

        BankConfigService bankConfigService = ServiceFactory.getBankConfigService();
        long accountNumber = bankConfigService.getNewAccountNumber();

        DepositAccount depositAccount = bankConfigService.findDepositInCatalog(id);
        depositAccount.setAmount(amount);
        depositAccount.setBalance(amount);
        depositAccount.setUserId(userId);
        depositAccount.setNumber(accountNumber);
        depositAccount.calculateExpirationDate();

        boolean flag = ServiceFactory.getDepositAccountSrvice().create(depositAccount);

        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            req.setAttribute("errorMessage", LocalizationUtils.CREATE_DEPOSIT_ERROR);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
