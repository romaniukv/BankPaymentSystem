package com.java.project.controller.servlets.admin;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.DepositAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Sets list of new credit accounts as request attribute "newCreditAccounts".
 * Sets list of available deposits in catalog as request attribute "availableDeposits".
 * Forwards to admin panel page.
 */
@WebServlet("/adminPanel")
public class AdminPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CreditAccount> newCreditAccounts = ServiceFactory.getCreditAccountService().selectNewAccounts();
        req.setAttribute("newCreditAccounts", newCreditAccounts);

        List<DepositAccount> availableDeposits = ServiceFactory.getBankConfigService().selectAvailableDepositAccountsFromCatalog();
        req.setAttribute("availableDeposits", availableDeposits);

        req.getRequestDispatcher("/views/admin/adminPanel.jsp").forward(req, resp);
    }

}
