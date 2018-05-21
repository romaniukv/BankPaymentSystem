package com.java.project.controller.servlets.admin;

import com.java.project.services.BankConfigService;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeDeposit")
public class RemoveDepositServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = AppUtils.getIdFromRequest(req, resp);
        new BankConfigService().removeDepositFromCatalog(id);
        resp.sendRedirect(req.getContextPath() + "/adminPanel");
    }
}
