package com.java.project.controller.servlets.credit;

import com.java.project.model.domain.AccountStatus;
import com.java.project.model.domain.CreditAccount;
import com.java.project.services.CreditAccountService;
import com.java.project.services.PaymentService;
import com.java.project.services.impl.CreditAccountServiceImpl;
import com.java.project.services.impl.PaymentServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lastPayments")
public class LastPaymentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.selectByUserId(userId);
        if (creditAccount != null && creditAccount.getStatus() != AccountStatus.CLOSED) {
            req.setAttribute("creditAccount", creditAccount);
            PaymentService paymentService = new PaymentServiceImpl();
            req.setAttribute("payments", paymentService.selectAllByAccountNumber(creditAccount.getNumber()));
            req.getRequestDispatcher("/views/credit/lastPayments.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "Credit account is closed or doesn't exist.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }
}
