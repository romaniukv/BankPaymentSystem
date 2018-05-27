package com.java.project.controller.servlets;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.DepositAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet shows deposit catalog.
 */
@WebServlet({"","/home"})
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DepositAccount> availableDepositAccounts = ServiceFactory.getBankConfigService()
                .selectAvailableDepositAccountsFromCatalog();
        req.setAttribute("depositAccounts", availableDepositAccounts);
        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }
}
