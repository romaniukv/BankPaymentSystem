package com.java.project.utils;

import com.java.project.model.entities.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();


    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static int storeRedirectAfterLoginUrl(String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;
            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(int redirectId) {
        return id_uri_map.get(redirectId);
    }

}
