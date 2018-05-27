package com.java.project.controller.filters;


import com.java.project.utils.LocalizationUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Sets default language if there is no such attribute in session.
 */
@WebFilter("/*")
public class LanguageFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if (session.getAttribute("language") == null) {
            session.setAttribute("language", "uk");
            LocalizationUtils.changeLanguage("uk");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
