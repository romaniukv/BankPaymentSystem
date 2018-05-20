package com.java.project.controller.servlets.admin;

import com.java.project.services.BankConfigService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeDeposit")
public class RemoveDepositServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        new BankConfigService().removeDepositFromCatalog(id);
        resp.sendRedirect(req.getContextPath() + "/adminPanel");
    }
}
