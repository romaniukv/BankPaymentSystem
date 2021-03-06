package com.java.project.controller.servlets.transactions;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.CreditAccount;
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
 * Servlet makes money transactions.
 * Checks whether user has credit account and if he hasn't forwards to error page.
 * If he has forwards to transfer money page, takes user's input
 * and then transfers money.
 */
@WebServlet("/transferMoney")
public class TransferMoneyServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(TransferMoneyServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccount creditAccount = ServiceFactory.getCreditAccountService().selectByUserId(userId);
        if (creditAccount != null) {
            req.setAttribute("creditAccount", creditAccount);
            req.getRequestDispatcher("/views/credit/transferMoney.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", LocalizationUtils.CANT_TRANSFER_MONEY);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long fromAccount = Long.valueOf(req.getParameter("fromAccount"));
        long toAccount = Long.valueOf(req.getParameter("toAccount"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        if (ServiceFactory.getTransactionService().transferMoney(fromAccount, toAccount, amount)) {
            logger.info("User " + AppUtils.getLoginedUser(req.getSession()).getUsername()
                    + " transferred money.");
            req.setAttribute("successMessage", LocalizationUtils.TRANSACTION_SUCCESS);
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            logger.error("User " + AppUtils.getLoginedUser(req.getSession()).getUsername()
                    + " failed to transfer money.");
            req.setAttribute("errorMessage", LocalizationUtils.TRANSACTION_FAILED);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
