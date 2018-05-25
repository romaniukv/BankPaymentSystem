package com.java.project.controller.servlets.admin;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.DepositAccount;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/editDeposit")
public class EditDepositServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = AppUtils.getIdFromRequest(req, resp);

        DepositAccount deposit= ServiceFactory.getBankConfigService().findDepositInCatalog(id);

        if (deposit != null) {
            req.setAttribute("deposit", deposit);
            req.getRequestDispatcher("/views/admin/editDeposit.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", LocalizationUtils.DEPOSIT_NOT_EXIST);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int term = Integer.valueOf(req.getParameter("term"));
        int id = AppUtils.getIdFromRequest(req, resp);
        BigDecimal rate = BigDecimal.valueOf(Double.valueOf(req.getParameter("rate")));

        if (ServiceFactory.getBankConfigService().updateDepositInCatalog(name, term, rate, id)) {
            resp.sendRedirect(req.getContextPath() + "/adminPanel");
        } else {
            req.setAttribute("errorMessage", LocalizationUtils.EDITING_ERROR);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
