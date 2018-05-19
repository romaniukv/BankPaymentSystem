package com.java.project.controller.filters;

import com.java.project.model.domain.Role;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = AppUtils.getLoginedUser(request.getSession());

        boolean hasPermission = user.getRole().equals(Role.ADMIN);

        if (!hasPermission) {
                request.setAttribute("errorMessage", "You are not allow to view this page.");
                request.getRequestDispatcher("/views/errorMessage.jsp").forward(request, response);
                return;
            }


        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}