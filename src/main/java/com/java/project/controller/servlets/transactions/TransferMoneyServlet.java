package com.java.project.controller.servlets.transactions;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.services.TransferMoneyService;
import com.java.project.model.domain.CreditAccount;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/transferMoney")
public class TransferMoneyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();
        CreditAccount creditAccount = new CreditAccountDAO().selectByUserId(userId);
        if (creditAccount != null) {
            req.setAttribute("creditAccount", creditAccount);
            req.getRequestDispatcher("/views/credit/transferMoney.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "Can not transfer money! No credit account found.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long fromAccount = Long.valueOf(req.getParameter("fromAccount"));
        long toAccount = Long.valueOf(req.getParameter("toAccount"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        if (new TransferMoneyService().transferMoney(fromAccount, toAccount, amount)) {
            req.setAttribute("successMessage", "Transaction success.");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Transaction failed.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
