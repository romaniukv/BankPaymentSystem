package com.java.project.controller.servlets;

import com.java.project.model.dao.CreditAccountDAO;
import com.java.project.model.entities.AccountStatus;
import com.java.project.model.entities.CreditAccount;
import com.java.project.model.entities.User;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AppUtils.getLoginedUser(req.getSession());
        CreditAccount creditAccount = new CreditAccountDAO().selectByUserId(user.getId());
        if (creditAccount != null && creditAccount.getStatus() != AccountStatus.CLOSED) {
            System.out.println(creditAccount.getStatus());
            req.setAttribute("creditAccount", creditAccount);
        }
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
