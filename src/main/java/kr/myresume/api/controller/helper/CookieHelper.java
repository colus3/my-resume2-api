package kr.myresume.api.controller.helper;

import kr.myresume.api.dto.Code;
import kr.myresume.api.exception.CodedException;
import kr.myresume.api.session.Session;
import kr.myresume.api.tool.CookieUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class CookieHelper {

    public static Cookie removeCookie() {
        return CookieUtils
                .builder()
                .setName(Session.COOKIE_NAME)
                .setValue(null)
                .setDomain(Session.COOKIE_DOMAIN)
                .setMaxAge(0)
                .setPath("/")
                .setHttpOnly(Session.COOKIE_HTTP_ONLY)
                .setSecure(Session.COOKIE_SECURE)
                .build();
    }

    public static Cookie createCookie(String token) {
        try {
            return CookieUtils.builder()
                    .setName(Session.COOKIE_NAME)
                    .setValue(URLEncoder.encode(token,
                                                "UTF-8"))
                    .setDomain(Session.COOKIE_DOMAIN)
                    .setPath("/")
                    .setHttpOnly(Session.COOKIE_HTTP_ONLY)
                    .setSecure(Session.COOKIE_SECURE)
                    .build();
        } catch (UnsupportedEncodingException e) {
            throw new CodedException(Code.COOKIE_CANNOT_BE_CREATED);
        }
    }
}
