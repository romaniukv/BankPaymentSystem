package com.java.project.controller.servlets.admin;

import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.DepositAccount;
import com.java.project.services.BankConfigService;
import com.java.project.services.CreditAccountService;
import com.java.project.services.impl.CreditAccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminPanel")
public class AdminPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        List<CreditAccount> newCreditAccounts = creditAccountService.selectNewAccounts();
        req.setAttribute("newCreditAccounts", newCreditAccounts);

        List<DepositAccount> availableDeposits = new BankConfigService().selectAvailableDepositAccountsFromCatalog();
        req.setAttribute("availableDeposits", availableDeposits);

        req.getRequestDispatcher("/views/admin/adminPanel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
