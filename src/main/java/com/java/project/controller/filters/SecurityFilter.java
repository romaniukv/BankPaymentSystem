package com.java.project.controller.filters;

import com.java.project.model.domain.Role;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Checks if user has permission to requested page.
 * If user hasn't permission filter sends user to error page.
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("SecurityFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = AppUtils.getLoginedUser(request.getSession());

        boolean hasPermission = user.getRole().equals(Role.ADMIN);

        if (!hasPermission) {
            logger.warn("Unauthorized access request to " + request.getRequestURI());
            request.setAttribute("errorMessage", LocalizationUtils.NOT_ALLOW);
            request.getRequestDispatcher("/views/errorMessage.jsp").forward(request, response);
            return;
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("SecurityFilter destroyed");
    }

}