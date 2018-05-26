package com.java.project.controller.servlets.admin;

import com.java.project.factory.ServiceFactory;
import com.java.project.utils.AppUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeDeposit")
public class RemoveDepositServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RemoveDepositServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = AppUtils.getIdFromRequest(req, resp);
        ServiceFactory.getBankConfigService().removeDepositFromCatalog(id);
        logger.info("Admin removed deposit from catalog: id = " + id);
        resp.sendRedirect(req.getContextPath() + "/adminPanel");
    }
}
