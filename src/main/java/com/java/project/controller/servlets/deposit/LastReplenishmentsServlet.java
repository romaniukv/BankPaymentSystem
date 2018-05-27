package com.java.project.controller.servlets.deposit;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.DepositAccount;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet used to show user's last replenishments.
 * Searches deposit account by id from request and if there is such sets it as request attribute.
 * Sets list of last replenishments as request attribute.
 * Forwards to last replenishments page.
 */
@WebServlet("/historyOfReplenishments")
public class LastReplenishmentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = AppUtils.getIdFromRequest(req, resp);

        DepositAccount depositAccount = ServiceFactory.getDepositAccountSrvice().findByKey(id);

        if (depositAccount != null && depositAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("depositAccount", depositAccount);

            req.setAttribute("replenishments", ServiceFactory.getDepositReplenishmentService()
                    .selectAllByAccountNumber(depositAccount.getNumber()));
            req.getRequestDispatcher("/views/deposit/lastReplenishments.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", LocalizationUtils.DEPOSIT_IS_CLOSED);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
