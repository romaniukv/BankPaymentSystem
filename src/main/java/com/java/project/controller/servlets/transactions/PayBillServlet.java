package com.java.project.controller.servlets.transactions;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
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

@WebServlet("/payBill")
public class PayBillServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PayBillServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccount creditAccount = ServiceFactory.getCreditAccountService().selectByUserId(userId);

        if (creditAccount != null) {
            req.setAttribute("creditAccount", creditAccount);
            req.getRequestDispatcher("/views/credit/payBill.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", LocalizationUtils.CANT_PAY_BILL);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AppUtils.getLoginedUser(req.getSession());
        String senderName = user.getFirstName() + " " + user.getLastName();
        String receiverName = req.getParameter("receiverName");
        String purpose = req.getParameter("purpose");

        long senderAccount = Long.valueOf(req.getParameter("senderAccount"));
        long receiverAccount = Long.valueOf(req.getParameter("receiverAccount"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        if (ServiceFactory.getPaymentService()
                .payBill(senderName, senderAccount, receiverName, receiverAccount, amount, purpose)) {
            logger.info("User " + user.getUsername() + " paid the bill.");
            req.setAttribute("successMessage", LocalizationUtils.PAYMENT_SUCCESS);
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            logger.error("User " + user.getUsername() + " failed to pay the bill.");
            req.setAttribute("errorMessage", LocalizationUtils.PAYMENT_FAILED);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

}
