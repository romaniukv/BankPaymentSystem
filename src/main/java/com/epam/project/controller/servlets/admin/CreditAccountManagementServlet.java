package com.epam.project.controller.servlets.admin;

import com.epam.project.model.dao.CreditAccountDAO;
import com.epam.project.model.dao.UserDAO;
import com.epam.project.model.entities.AccountStatus;
import com.epam.project.model.entities.CreditAccount;
import com.epam.project.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/creditAccountManagement")
public class CreditAccountManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.valueOf(req.getParameter("id"));

        CreditAccount creditAccount = new CreditAccountDAO().findByKey(accountId);

        if (creditAccount != null) {
            req.setAttribute("creditAccount", creditAccount);

            User accountOwner = new UserDAO().findByKey(creditAccount.getUserId());
            req.setAttribute("accountOwner", accountOwner);

            req.setAttribute("statuses", AccountStatus.values());
            req.getRequestDispatcher("/views/adminViews/creditAccountManagement.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountStatus status = AccountStatus.valueOf(req.getParameter("accountStatus"));

        int id = Integer.valueOf(req.getParameter("id"));

        CreditAccountDAO creditAccountDAO = new CreditAccountDAO();
        CreditAccount creditAccount = creditAccountDAO.findByKey(id);

        boolean flag = false;
        if (creditAccount != null) {
            creditAccount.setStatus(status);
            flag = creditAccountDAO.update(creditAccount);
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
