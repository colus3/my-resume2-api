package me.programmeris.myresume.api.session;

import me.programmeris.myresume.api.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class Session {
    public static ThreadLocal<Boolean> isLoggedIn = new ThreadLocal<Boolean>() {{ set(false); }};
    public static ThreadLocal<String> key = new ThreadLocal<>();
    public static ThreadLocal<User> user = new ThreadLocal<>();

    public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";

    public boolean isLoggedIn() {
        return isLoggedIn.get();
    }

    public String getKey() {
        return key.get();
    }
}
