package com.java.project.controller.servlets.admin;

import com.java.project.model.domain.DepositAccount;
import com.java.project.services.BankConfigService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addNewDeposit")
public class AddNewDepositServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/addNewDeposit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int term = Integer.valueOf(req.getParameter("term"));
        BigDecimal rate = BigDecimal.valueOf(Double.valueOf(req.getParameter("rate")));

        if (new BankConfigService().addDepositToCatalog(new DepositAccount(name, term, rate))) {
            req.setAttribute("successMessage", "New deposit added.");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Can add deposit. Try again");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
