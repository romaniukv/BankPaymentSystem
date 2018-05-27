package com.java.project.controller.filters;

import com.java.project.entities.TestEntities;
import com.java.project.utils.AppUtils;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


public class SecurityFilterTest {

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
    public void userHasNotPermission() throws ServletException, IOException {
        SecurityFilter securityFilter = new SecurityFilter();

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(AppUtils.getLoginedUser(request.getSession())).thenReturn(TestEntities.getTestUser2());

        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(request.getRequestDispatcher("/views/errorMessage.jsp")).thenReturn(requestDispatcher);

        securityFilter.doFilter(request, response, filterChain);

        Mockito.verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void userHasPermission() throws ServletException, IOException {
        SecurityFilter securityFilter = new SecurityFilter();

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(AppUtils.getLoginedUser(request.getSession())).thenReturn(TestEntities.getTestUser());

        securityFilter.doFilter(request, response, filterChain);

        Mockito.verify(filterChain).doFilter(request, response);

    }

}