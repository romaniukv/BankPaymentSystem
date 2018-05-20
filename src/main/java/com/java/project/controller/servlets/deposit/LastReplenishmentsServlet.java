package com.java.project.controller.servlets.deposit;

import com.java.project.model.dao.DepositAccountDAO;
import com.java.project.model.dao.DepositReplenishmentDAO;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.DepositAccount;

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

        DepositAccount depositAccount = new DepositAccountDAO().findByKey(id);
        if (depositAccount != null && depositAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("depositAccount", depositAccount);
            System.out.println(depositAccount.getNumber());
            req.setAttribute("replenishments", new DepositReplenishmentDAO().selectAllByAccountNumber(depositAccount.getNumber()));
            req.getRequestDispatcher("/views/deposit/lastReplenishments.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Deposit account is closed or doesn't exist.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }


    }
}
