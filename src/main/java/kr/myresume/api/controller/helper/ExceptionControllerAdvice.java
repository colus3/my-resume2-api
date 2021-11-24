package kr.myresume.api.controller.helper;

import lombok.extern.slf4j.Slf4j;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.response.Empty;
import kr.myresume.api.dto.response.Response;
import kr.myresume.api.exception.CodedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice(annotations = { RestController.class })
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CodedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<Empty> handleCodedException(CodedException ce,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {

        log.error("CodedException handler : {} {}",
                ce.getCode(),
                request.getLocale());

        if (Code.INVALID_SESSION == ce.getCode()) {
            response.addCookie(CookieHelper.removeCookie());
        }
        log.error("Code : [{}], Message : [{}]", ce.getCode(), ce.getMessage(), ce);

        return Response.create(ce.getCode(), ce.getMessage());
    }

    @ExceptionHandler(value = MissingRequestCookieException.class)
    @ResponseBody
    public Response<Empty> handleCookieException(MissingRequestCookieException ce,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {

        log.error("CookieException handler : {} {}", ce.getClass().getName(), ce.getMessage(), ce);
        return Response.create(Code.COOKIE_NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Empty> handleDefaultException(Exception e,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) {

        log.error("exception handler : {} {}", e.getClass().getName(), e.getMessage(), e);

        return Response.create(e.getMessage());
    }
}
