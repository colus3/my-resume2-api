package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import me.programmeris.myresume.api.dto.Code;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T extends ResponseData> {

    private Code code;
    private String message;
    private T data;

    public static <T extends ResponseData> Response<T> create(Code code, T data) {

        return new Response<>(code, data);
    }

    public static Response create(Code code, String message) {

        return new Response<>(code, message, null);
    }

    public static Response create(Code code) {

        return new Response<>(code, null);
    }

    private Response(Code code, T data) {
        this.code = code;
        this.data = data;
    }

    private Response(Code code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
