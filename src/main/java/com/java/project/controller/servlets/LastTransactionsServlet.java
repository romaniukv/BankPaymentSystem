package com.java.project.controller.servlets;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.dao.PaymentDAO;
import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lastTransactions")
public class LastTransactionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccount creditAccount = new CreditAccountDAO().selectByUserId(userId);
        if (creditAccount != null && creditAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("creditAccount", creditAccount);
            req.setAttribute("transactions", new TransactionDAO().selectAllByAccountNumber(creditAccount.getNumber()));
        }  else {
            req.setAttribute("errorMessage", "Credit account is closed or doesn't exist.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/views/lastTransactions.jsp").forward(req, resp);
    }
}
