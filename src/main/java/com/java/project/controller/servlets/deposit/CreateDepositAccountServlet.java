package com.java.project.controller.servlets.deposit;

import com.java.project.factory.ServiceFactory;
import com.java.project.services.BankConfigService;
import com.java.project.model.domain.DepositAccount;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


@WebServlet("/createDepositAccount")
public class CreateDepositAccountServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(CreateDepositAccountServlet.class);

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
        User user = AppUtils.getLoginedUser(req.getSession());
        int id = AppUtils.getIdFromRequest(req, resp);

        BankConfigService bankConfigService = ServiceFactory.getBankConfigService();
        long accountNumber = bankConfigService.getNewAccountNumber();

        DepositAccount depositAccount = bankConfigService.findDepositInCatalog(id);
        depositAccount.setAmount(amount);
        depositAccount.setBalance(amount);
        depositAccount.setUserId(user.getId());
        depositAccount.setNumber(accountNumber);
        depositAccount.calculateExpirationDate();

        boolean flag = ServiceFactory.getDepositAccountSrvice().create(depositAccount);

        if (flag) {
            logger.info("User " + user.getUsername() + " opened new deposit account.");
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            logger.error("User " + user.getUsername() + "failed to open new deposit account.");
            req.setAttribute("errorMessage", LocalizationUtils.CREATE_DEPOSIT_ERROR);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
