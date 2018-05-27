package com.java.project.controller.servlets.admin;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.DepositAccount;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlets used to show information about user to admin.
 * Searches user by id from request and if there is such user
 * sets user and list of user's deposit accounts as request attributes.
 * Forwards to user info page.
 */
@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = AppUtils.getIdFromRequest(req, resp);

        User user = ServiceFactory.getUserService().findByKey(id);

        if (user != null) {
            req.setAttribute("userInfo", user);

            List<DepositAccount> depositAccounts = ServiceFactory.getDepositAccountSrvice().selectByUserId(user.getId());
            if (depositAccounts.size() != 0) {
                req.setAttribute("depositAccounts", depositAccounts);
            }
            req.getRequestDispatcher("/views/admin/userInfo.jsp").forward(req, resp);

        } else {
            req.setAttribute("errorMessage", LocalizationUtils.USER_NOT_EXIST);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
