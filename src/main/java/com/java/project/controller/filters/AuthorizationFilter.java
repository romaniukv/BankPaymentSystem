package com.java.project.controller.filters;

import com.java.project.model.entities.User;
import com.java.project.utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = AppUtils.getLoginedUser(request.getSession());
        if (user != null) {
            chain.doFilter(request, response);
        }
        else {
            String requestUri = request.getRequestURI();
            System.out.println(requestUri);
            int redirectId = AppUtils.storeRedirectAfterLoginUrl(requestUri);

            response.sendRedirect(request.getContextPath() + "/login?redirectId=" + redirectId);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
