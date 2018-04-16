package com.epam.project.controller.filters;

import com.epam.project.model.entities.User;
import com.epam.project.utils.AppUtils;
import com.epam.project.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
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

        String servletPath = request.getServletPath();

        User loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (SecurityUtils.isSecurityPage(request)) {

            if (loginedUser == null) {
                response.sendRedirect((request.getContextPath() + "/login"));
                return;
            }

            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecurityUtils.hasPermission(loginedUser);
            System.out.println("before");
            if (!hasPermission) {
                System.out.println("during");
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp").forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}