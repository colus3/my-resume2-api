package me.programmeris.myresume.api.controller.helper;

import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.exception.CodedException;
import me.programmeris.myresume.api.session.Session;
import me.programmeris.myresume.api.tool.CookieUtils;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookieHelper {
    public static Cookie removeCookie() {
        return CookieUtils
                .builder()
                .setName(Session.ACCESS_TOKEN_COOKIE_NAME)
                .setValue(null)
                .setMaxAge(0)
                .setPath("/")
                .build();
    }

    public static Cookie createCookie(String token) {
        try {
            return CookieUtils.builder()
                    .setName(Session.ACCESS_TOKEN_COOKIE_NAME)
                    .setValue(URLEncoder.encode(token,
                                                "UTF-8"))
                    .setPath("/")
                    .setMaxAge(0)
                    .build();
        } catch (UnsupportedEncodingException uee) {
            throw new CodedException(Code.COOKIE_CANNOT_BE_CREATED);
        }
    }
}
