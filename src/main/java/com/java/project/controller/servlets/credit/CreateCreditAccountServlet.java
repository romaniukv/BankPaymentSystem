package com.java.project.controller.servlets.credit;

import com.java.project.factory.ServiceFactory;
import com.java.project.services.BankConfigService;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
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

/**
 * Servlet used to create new credit account.
 * Checks whether a user already has a credit account and if he has forwards to error page.
 * If he hasn't forwards to create credit account page and then creates new account in database.
 */
@WebServlet("/createCreditAccount")
public class CreateCreditAccountServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(CreateCreditAccountServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = AppUtils.getLoginedUser(req.getSession());

        CreditAccount creditAccount = ServiceFactory.getCreditAccountService().selectByUserId(user.getId());

        if (creditAccount != null && creditAccount.getStatus() == AccountStatus.OPENED) {

            req.setAttribute("errorMessage", LocalizationUtils.ALREADY_HAVE_CREDIT);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);

        } else if (creditAccount != null && creditAccount.getStatus() == AccountStatus.UNDER_CONSIDERATION) {

            req.setAttribute("errorMessage", LocalizationUtils.CANT_CREATE_CREDIT);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);

        } else {

            req.getSession().setAttribute("creditLimits", ServiceFactory.getBankConfigService().selectCreditLimits());
            req.getRequestDispatcher("/views/credit/createCreditAccount.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditLimit"));
        User user = AppUtils.getLoginedUser(req.getSession());

        BankConfigService bankConfigService = ServiceFactory.getBankConfigService();
        BigDecimal creditRate = bankConfigService.selectCreditLimits().get(creditLimit);
        long accountNumber = bankConfigService.getNewAccountNumber();


        CreditAccount creditAccount = new CreditAccount(accountNumber, user.getId(), creditLimit, creditRate);
        creditAccount.calculateExpirationDate();
        creditAccount.setStatus(AccountStatus.UNDER_CONSIDERATION);
        boolean flag = ServiceFactory.getCreditAccountService().create(creditAccount);

        if (flag) {
            logger.info("User " + user.getUsername() + " opened new credit account.");
            req.setAttribute("successMessage", LocalizationUtils.CREATE_CREDIT_SUCCESS);
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        }
        else {
            logger.error("User " + user.getUsername() + "failed to open new credit account.");
            req.setAttribute("errorMessage", LocalizationUtils.CREATE_CREDIT_ERROR);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
