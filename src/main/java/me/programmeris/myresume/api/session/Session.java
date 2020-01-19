package me.programmeris.myresume.api.session;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.programmeris.myresume.api.dto.response.UserDto;
import me.programmeris.myresume.api.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {
    public static ThreadLocal<Boolean> isLoggedIn = new ThreadLocal<Boolean>() {{ set(false); }};
    public static ThreadLocal<String> token = new ThreadLocal<>();
    public static ThreadLocal<User> user = new ThreadLocal<>();

    public static final String COOKIE_NAME = "access_token";
    public static String COOKIE_DOMAIN = "localhost";
    public static Boolean COOKIE_HTTP_ONLY = false;
    public static Boolean COOKIE_SECURE = false;

    @Autowired
    public Session(@Value("${cookie.domain}") String cookieDomain,
                   @Value("${cookie.http_only}") Boolean cookieHttpOnly,
                   @Value("${cookie.secure}") Boolean cookieSecure) {
        Session.COOKIE_DOMAIN = cookieDomain;
        Session.COOKIE_HTTP_ONLY = cookieHttpOnly;
        Session.COOKIE_SECURE = cookieSecure;
    }

    public boolean isLoggedIn() {
        return isLoggedIn.get();
    }

    public String getToken() {
        return token.get();
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
