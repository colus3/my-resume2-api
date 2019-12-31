package me.programmeris.myresume.api.dto;

public enum Code {
    SUCCESS(1000), FAILURE(2000), USER_DEFINED(9000);

    int code;
    Code(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
