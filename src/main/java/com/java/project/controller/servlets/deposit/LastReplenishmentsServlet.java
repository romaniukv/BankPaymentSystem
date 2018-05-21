package com.java.project.controller.servlets.deposit;

import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.DepositAccount;
import com.java.project.services.DepositAccountService;
import com.java.project.services.DepositReplenishmentService;
import com.java.project.services.impl.DepositAccountServiceImpl;
import com.java.project.services.impl.DepositReplenishmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/historyOfReplenishments")
public class LastReplenishmentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));

        DepositAccountService depositAccountService = new DepositAccountServiceImpl();
        DepositAccount depositAccount = depositAccountService.findByKey(id);
        if (depositAccount != null && depositAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("depositAccount", depositAccount);
            DepositReplenishmentService replenishmentService = new DepositReplenishmentServiceImpl();
            req.setAttribute("replenishments", replenishmentService.selectAllByAccountNumber(depositAccount.getNumber()));
            req.getRequestDispatcher("/views/deposit/lastReplenishments.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Deposit account is closed or doesn't exist.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }


    }
}
