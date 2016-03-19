package com.medisys.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static String authorization(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            auth = request.getParameter("Authorization"); //needed for downloading files
        }
        return auth;
    }

    public static String origin(HttpServletRequest request) {
        String origin = request.getHeader("origin");
        if (origin == null) {
            origin = request.getHeader("domain");//note that this is mostly used by test rest calls, we get origin from browser
        }
        if (origin == null) {
            origin = request.getParameter("domain"); //needed for downloading files
        }
        return origin;
    }
}
