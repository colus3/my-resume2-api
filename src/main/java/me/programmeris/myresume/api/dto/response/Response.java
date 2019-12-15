package me.programmeris.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.programmeris.myresume.api.dto.Code;
import org.springframework.data.domain.Page;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T extends ResponseData> {

    private Code code;
    private String message;
    private T data;
    @JsonProperty(value = "paging")
    private Page<T> pagingData;

    public static <T extends ResponseData> Response<T> create(Code code, T data) {

        return new Response<>(code, data);
    }

    public static <T extends ResponseData> Response create(Code code, Page<T> data) {

        return new Response<>(code, data);
    }

    public static Response create(Code code, String message) {

        return new Response<>(code, message, null);
    }

    public static Response create(String message) {

        return new Response(message);
    }

    public static Response create(Code code) {

        return new Response(code);
    }

    private Response(Code code, T data) {
        this.code = code;
        this.data = data;
    }

    private Response(Code code, Page<T> data) {
        this.code = code;
        this.pagingData = data;
    }

    private Response(Code code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Response(Code code) {
        this.code = code;
    }

    private Response(String message) {
        this.code = Code.USER_DEFINED;
        this.message = message;
    }
}
