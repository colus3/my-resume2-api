package me.programmeris.myresume.api.controller.helper;

import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.response.Empty;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.exception.CodedException;
import me.programmeris.myresume.api.session.Session;
import me.programmeris.myresume.api.tool.CookieUtils;
import org.springframework.http.HttpStatus;
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

        log.error("call codedException handler : {} {}",
                ce.getCode(),
                request.getLocale());

        if (Code.INVALID_SESSION == ce.getCode()) {
            response.addCookie(CookieUtils
                    .builder()
                    .setName(Session.ACCESS_TOKEN_COOKIE_NAME)
                    .setValue(null)
                    .setMaxAge(0)
                    .setPath("/")
                    .build());
        } else {
            log.error(ce.getMessage(), ce);
        }

        return Response.create(ce.getCode(), ce.getMessage());
    }
}
