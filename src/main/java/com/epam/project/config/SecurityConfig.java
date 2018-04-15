package com.epam.project.config;

import java.util.*;

public class SecurityConfig {

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userProfile");
        urlPatterns1.add("/adminPanel");

        mapConfig.put(Role.ADMIN.toString(), urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userProfile");

        mapConfig.put(Role.USER.toString(), urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
