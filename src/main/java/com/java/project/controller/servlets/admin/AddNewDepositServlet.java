package com.java.project.controller.servlets.admin;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.DepositAccount;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Servlet for adding new deposit in catalog by admin.
 */
@WebServlet("/addNewDeposit")
public class AddNewDepositServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddNewDepositServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/addNewDeposit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int term = Integer.valueOf(req.getParameter("term"));
        BigDecimal rate = BigDecimal.valueOf(Double.valueOf(req.getParameter("rate")));

        if (ServiceFactory.getBankConfigService().addDepositToCatalog(new DepositAccount(name, term, rate))) {
            logger.info("Admin " + AppUtils.getLoginedUser(req.getSession()).getUsername()
                    + " added new deposit to catalog");
            req.setAttribute("successMessage", LocalizationUtils.NEW_DEPOSIT_ADDED);
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            logger.error("Admin " + AppUtils.getLoginedUser(req.getSession()).getUsername()
                    + " failed to add new deposit to catalog");
            req.setAttribute("errorMessage", LocalizationUtils.CANT_ADD_DEPOSIT);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
