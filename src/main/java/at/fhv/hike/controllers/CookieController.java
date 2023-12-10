package at.fhv.hike.controllers;

import jakarta.servlet.http.Cookie;

public class CookieController {
    public static String getLogedInUserId(Cookie[] cookies){
        String sessionToken=null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    // Found the cookie, now do something with it
                    sessionToken = cookie.getValue();
                    return sessionToken;
                }
            }
        }
        return null;
    }
}
