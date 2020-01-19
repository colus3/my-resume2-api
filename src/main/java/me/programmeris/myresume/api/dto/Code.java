package me.programmeris.myresume.api.dto;

public enum Code {
    SUCCESS(1000), FAILURE(2000),
    INVALID_SESSION(3000),
    COOKIE_CANNOT_BE_CREATED(3001),
    COOKIE_NOT_FOUND(3002),
    // User
    INCORRECT_USERNAME_OR_PASSWORD(4000),
    NOT_LOGGED_IN(4001),
    // Content
    NOT_EXISTS_CONTENT(5000),
    // Resume

    // ETC
    ETC(8000),
    USER_DEFINED(9000), UNDEFINED(9500);

    int code;
    Code(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
