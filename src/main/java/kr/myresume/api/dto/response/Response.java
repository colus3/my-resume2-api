package kr.myresume.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.myresume.api.exception.CodedException;
import lombok.Data;
import kr.myresume.api.dto.Code;
import org.springframework.data.domain.Page;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T extends ResponseData> {

    private Integer code;
    private String message;
    private T data;
    @JsonProperty(value = "dataList")
    private Page<T> pagingData;

    public static <T extends ResponseData> Response<T> create(Code code, T data) {

        return new Response<>(code, data);
    }

    public static <T extends ResponseData> Response<T> create(Code code, Page<T> data) {

        return new Response<>(code, data);
    }

    public static Response<Empty> create(Code code, String message) {

        return new Response<>(code, message, null);
    }

    public static Response<Empty> create(String message) {

        return new Response<>(message);
    }

    public static Response<Empty> create(Code code) {

        return new Response<>(code);
    }

    private Response(Code code, T data) {
        this.code = code.getCode();
        this.message = code.name();
        if (data == null) throw new CodedException(Code.NO_DATA);
        this.data = data;
    }

    private Response(Code code, Page<T> data) {
        this.code = code.getCode();
        this.message = code.name();
        if (data == null || data.getNumberOfElements() <= 0) throw new CodedException(Code.NO_DATA);
        this.pagingData = data;
    }

    private Response(Code code, String message, T data) {
        this.code = code.getCode();
        this.message = code.name();
        if (code == Code.USER_DEFINED) {
            this.message = message;
        }
        this.data = data;
    }

    private Response(Code code) {
        this.code = code.getCode();
        this.message = code.name();
    }

    private Response(String message) {
        this.code = Code.USER_DEFINED.getCode();
        this.message = message;
    }
}
