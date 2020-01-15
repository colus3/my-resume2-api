package me.programmeris.myresume.api.exception;

import lombok.Getter;
import me.programmeris.myresume.api.dto.Code;

@Getter
public class CodedException extends RuntimeException {

    private Code code;

    public CodedException(String message) {
        this(Code.UNDEFINED, message);
    }
    public CodedException(Code code) {
        this(code, null);
    };
    public CodedException(Code code, String message) {
        super(message);
        this.code = code;
    };
}
