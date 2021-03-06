package com.java.project.controller.servlets;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.DepositAccount;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for user's profile.
 * Sets user's credit account and list of deposit accounts as request attributes.
 * Forwards to profile page.
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccount creditAccount = ServiceFactory.getCreditAccountService().selectByUserId(userId);
        if (creditAccount != null && creditAccount.getStatus() == AccountStatus.OPENED) {
            req.setAttribute("creditAccount", creditAccount);
        }

        List<DepositAccount> depositAccounts = ServiceFactory.getDepositAccountSrvice().selectByUserId(userId);
        if (depositAccounts.size() != 0) {
            req.setAttribute("depositAccounts", depositAccounts);
        }

        req.getRequestDispatcher("/views/profile/profile.jsp").forward(req, resp);
    }

}
