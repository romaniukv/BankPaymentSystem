package com.java.project.utils;

import com.java.project.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_url_map = new HashMap<>();
    private static final Map<String, Integer> url_id_map = new HashMap<>();

    public static int getIdFromRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = -1;
        try {
            id = Integer.valueOf(request.getParameter("id"));
        } catch (Exception e) {
            request.getRequestDispatcher("/views/errorMessage.jsp").forward(request, response);
        }
        return id;
    }


    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static int storeRedirectAfterLoginUrl(String requestUri) {
        Integer id = url_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;
            url_id_map.put(requestUri, id);
            id_url_map.put(id, requestUri);
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(int redirectId) {
        return id_url_map.get(redirectId);
    }

}
