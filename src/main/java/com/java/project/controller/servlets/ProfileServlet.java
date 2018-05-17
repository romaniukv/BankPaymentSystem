package com.java.project.controller.servlets;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.dao.DepositAccountDAO;
import com.java.project.model.dao.TransactionDAO;
import com.java.project.model.entities.AccountStatus;
import com.java.project.model.entities.CreditAccount;
import com.java.project.model.entities.DepositAccount;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccount creditAccount = new CreditAccountDAO().selectByUserId(userId);
        if (creditAccount != null && creditAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("creditAccount", creditAccount);
            req.setAttribute("transactions", new TransactionDAO().selectAll());
        }


        List<DepositAccount> depositAccounts = new DepositAccountDAO().selectByUserId(userId);
        if (depositAccounts.size() != 0) {
            req.setAttribute("depositAccounts", depositAccounts);
        }

        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

}
