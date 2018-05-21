package com.java.project.controller.servlets.admin;

import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
import com.java.project.services.CreditAccountService;
import com.java.project.services.UserService;
import com.java.project.services.impl.CreditAccountServiceImpl;
import com.java.project.services.impl.UserServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/creditAccountManagement")
public class CreditAccountManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = AppUtils.getIdFromRequest(req, resp);

        CreditAccount creditAccount = new CreditAccountServiceImpl().findByKey(accountId);

        if (creditAccount != null) {
            req.setAttribute("creditAccount", creditAccount);

            UserService userService = new UserServiceImpl();
            User accountOwner = userService.findByKey(creditAccount.getUserId());
            req.setAttribute("accountOwner", accountOwner);

            req.setAttribute("statuses", AccountStatus.values());
            req.getRequestDispatcher("/views/admin/creditAccountManagement.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountStatus status = AccountStatus.valueOf(req.getParameter("accountStatus"));

        int id = AppUtils.getIdFromRequest(req, resp);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.findByKey(id);

        boolean flag = false;
        if (creditAccount != null) {
            creditAccount.setStatus(status);
            flag = creditAccountService.update(creditAccount);
        }

        if (flag) {
            req.setAttribute("successMessage", "Changes successfully saved.");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "Can not manage this account.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
