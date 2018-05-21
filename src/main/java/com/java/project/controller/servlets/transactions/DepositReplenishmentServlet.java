package com.java.project.controller.servlets.transactions;

import com.java.project.model.domain.DepositAccount;
import com.java.project.services.DepositAccountService;
import com.java.project.services.DepositReplenishmentService;
import com.java.project.services.impl.DepositAccountServiceImpl;
import com.java.project.services.impl.DepositReplenishmentServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/replenishDeposit")
public class DepositReplenishmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = AppUtils.getIdFromRequest(req, resp);
        DepositAccountService depositAccountService = new DepositAccountServiceImpl();
        DepositAccount depositAccount = depositAccountService.findByKey(id);
        if (depositAccount != null) {
            req.setAttribute("depositAccount", depositAccount);
            req.getRequestDispatcher("/views/deposit/depositReplenishment.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "Deposit account not found.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long senderAccountNumber = Long.valueOf(req.getParameter("senderAccountNumber"));
        long receiverAccountNumber = Long.valueOf(req.getParameter("receiverAccountNumber"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        DepositReplenishmentService replenishmentService = new DepositReplenishmentServiceImpl();
        if (replenishmentService.replenishDeposit(senderAccountNumber, receiverAccountNumber, amount)) {
            req.setAttribute("successMessage", "Transaction success.");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Can not replenish deposit. Try again.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

}
