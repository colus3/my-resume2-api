package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.request.LoginForm;
import me.programmeris.myresume.api.dto.request.SignUpForm;
import me.programmeris.myresume.api.dto.response.Empty;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.service.UserService;
import me.programmeris.myresume.api.session.Session;
import me.programmeris.myresume.api.tool.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
public class AuthenticationApiController {

    private final UserService userService;

    @PostMapping("/login")
    public Response<Empty> login(HttpServletResponse response, @RequestBody LoginForm loginForm) throws UnsupportedEncodingException {

        log.debug("loginForm {}", loginForm);
        String token = userService.login(loginForm);

        response.addCookie(CookieUtils.builder()
                .setName(Session.ACCESS_TOKEN_COOKIE_NAME)
                .setValue(URLEncoder.encode(token, "UTF-8"))
                .setPath("/")
                .setMaxAge(0)
                .build());

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("/logout")
    public Response<Empty> logout(@CookieValue(Session.ACCESS_TOKEN_COOKIE_NAME) String token,
                                  HttpServletResponse response) {

        if (StringUtils.isNotEmpty(token)) {
            userService.logout(token);
        }

        response.addCookie(CookieUtils.builder()
                .setName(Session.ACCESS_TOKEN_COOKIE_NAME)
                .setValue(null)
                .setPath("/")
                .setMaxAge(0)
                .build());

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("/signUp")
    public Response<Empty> signUp(@RequestBody SignUpForm signUpForm) {

        userService.signUp(signUpForm);

        return Response.create(Code.SUCCESS);
    }
}
