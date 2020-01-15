package me.programmeris.myresume.api.tool;

import me.programmeris.myresume.api.exception.CodedException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.List;

public class CookieUtils {
    private CookieUtils() { }

    public static String getValue(Cookie[] cookies, String key) {
        Cookie cookie = getCookie(cookies, key);
        return cookie == null ? null : cookie.getValue();
    }

    public static Cookie getCookie(Cookie[] cookies, String key) {
        if (cookies == null) return null;
        List<Cookie> cookieList = Arrays.asList(cookies);
        return cookieList.stream()
                .filter(e -> StringUtils.equals(key, e.getName()))
                .findFirst()
                .orElse(null);
    }

    public static CookieUtils.CookieBuilder builder() {
        return new CookieUtils.CookieBuilder();
    }

    public static class CookieBuilder {
        private String name;
        private String value;
        private Boolean httpOnly;
        private String path;
        private String domain;
        private Integer maxAge;
        private String comment;
        private Boolean secure;
        private Integer version;

        private CookieBuilder() {
            this.name = null;
            this.value = null;
            this.httpOnly = null;
            this.path = null;
            this.domain = null;
            this.maxAge = null;
            this.comment = null;
            this.secure = null;
            this.version = null;
        }

        public CookieUtils.CookieBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CookieUtils.CookieBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public CookieUtils.CookieBuilder setHttpOnly(Boolean isHttpOnly) {
            this.httpOnly = isHttpOnly;
            return this;
        }

        public CookieUtils.CookieBuilder setPath(String uri) {
            this.path = uri;
            return this;
        }

        public CookieUtils.CookieBuilder setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        public CookieUtils.CookieBuilder setMaxAge(Integer expiry) {
            this.maxAge = expiry;
            return this;
        }

        public CookieUtils.CookieBuilder setComment(String purpose) {
            this.comment = purpose;
            return this;
        }

        public CookieUtils.CookieBuilder setSecure(Boolean flag) {
            this.secure = flag;
            return this;
        }

        public CookieUtils.CookieBuilder setVersion(Integer v) {
            this.version = v;
            return this;
        }

        public Cookie build() throws RuntimeException {
            if (StringUtils.isEmpty(this.name)) {
                throw new CodedException("Cookie creation failed.");
            } else {
                Cookie cookie = new Cookie(this.name, this.value);
                if (this.httpOnly != null) {
                    cookie.setHttpOnly(this.httpOnly);
                }

                if (this.path != null) {
                    cookie.setPath(this.path);
                }

                if (this.domain != null) {
                    cookie.setDomain(this.domain);
                }

                if (this.maxAge != null) {
                    cookie.setMaxAge(this.maxAge);
                }

                if (this.comment != null) {
                    cookie.setComment(this.comment);
                }

                if (this.secure != null) {
                    cookie.setSecure(this.secure);
                }

                if (this.version != null) {
                    cookie.setVersion(this.version);
                }

                return cookie;
            }
        }
    }
}
