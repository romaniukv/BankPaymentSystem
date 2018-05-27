package com.java.project.controller.servlets.credit;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet used to show user's last transactions.
 * Searches credit account by user id and if there is such sets it as request attribute.
 * Sets list of last transactions as request attribute.
 * Forwards to last transactions page.
 */
@WebServlet("/lastTransactions")
public class LastTransactionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccount creditAccount = ServiceFactory.getCreditAccountService().selectByUserId(userId);

        if (creditAccount != null && creditAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("creditAccount", creditAccount);

            req.setAttribute("transactions", ServiceFactory.getTransactionService()
                    .selectAllByAccountNumber(creditAccount.getNumber()));

            req.getRequestDispatcher("/views/credit/lastTransactions.jsp").forward(req, resp);
        }  else {
            req.setAttribute("errorMessage", LocalizationUtils.CREDIT_IS_CLOSED);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }


    }
}
