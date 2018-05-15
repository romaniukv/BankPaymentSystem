package com.epam.project.controller.servlets;

import com.epam.project.model.dao.BankConfigDAO;
import com.epam.project.model.entities.DepositAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"","/home"})
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DepositAccount> availableDepositAccounts = new BankConfigDAO().selectAvailableDepositAccounts();
        req.setAttribute("depositAccounts", availableDepositAccounts);
        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }
}
