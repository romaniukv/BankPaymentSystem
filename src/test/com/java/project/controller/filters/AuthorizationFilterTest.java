package com.java.project.controller.filters;

import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class AuthorizationFilterTest {

    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void userNotAuthorized() throws ServletException, IOException {
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(AppUtils.getLoginedUser(request.getSession())).thenReturn(null);
        Mockito.when(request.getContextPath()).thenReturn("app");
        authorizationFilter.doFilter(request, response, filterChain);

        Mockito.verify(response).sendRedirect("app/login?redirectId=0");
    }

    @Test
    public void userIsAuthorized() throws ServletException, IOException {
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(AppUtils.getLoginedUser(request.getSession())).thenReturn(new User());
        Mockito.when(request.getContextPath()).thenReturn("app");
        authorizationFilter.doFilter(request, response, filterChain);

        Mockito.verify(filterChain).doFilter(request, response);
    }

}