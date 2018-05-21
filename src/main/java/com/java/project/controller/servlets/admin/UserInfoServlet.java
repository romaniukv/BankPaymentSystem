package com.java.project.controller.servlets.admin;

import com.java.project.model.domain.DepositAccount;
import com.java.project.model.domain.User;
import com.java.project.services.DepositAccountService;
import com.java.project.services.UserService;
import com.java.project.services.impl.DepositAccountServiceImpl;
import com.java.project.services.impl.UserServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = AppUtils.getIdFromRequest(req, resp);
        UserService userService = new UserServiceImpl();
        User user = userService.findByKey(id);

        if (user != null) {
            req.setAttribute("user", user);
            DepositAccountService depositAccountService = new DepositAccountServiceImpl();
            List<DepositAccount> depositAccounts = depositAccountService.selectByUserId(user.getId());
            if (depositAccounts.size() != 0) {
                req.setAttribute("depositAccounts", depositAccounts);
            }
            req.getRequestDispatcher("/views/admin/userInfo.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Such user doesn't exist.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
