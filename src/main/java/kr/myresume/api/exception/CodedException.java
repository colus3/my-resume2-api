package kr.myresume.api.exception;

import lombok.Getter;
import kr.myresume.api.dto.Code;

@Getter
public class CodedException extends RuntimeException {

    private Code code;

    public CodedException(String message) {
        this(Code.UNDEFINED, message);
    }
    public CodedException(Code code) {
        this(code, code.name());
    };
    public CodedException(Code code, String message) {
        super(message);
        this.code = code;
    };
}
