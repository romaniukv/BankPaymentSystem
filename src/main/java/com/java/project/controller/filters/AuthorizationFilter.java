package com.java.project.controller.filters;

import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Authorization Filter checks if user attribute is present in session.
 * If there is no user in session filter saves redirect Url sends redirect to login page.
 */
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
            String requestUrl = String.valueOf(request.getRequestURL());
            String parameters;
            if ((parameters = request.getQueryString()) != null)
                requestUrl += "?" + parameters;
            int redirectId = AppUtils.storeRedirectUrl(requestUrl);

            response.sendRedirect(request.getContextPath() + "/login?redirectId=" + redirectId);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
