package me.programmeris.myresume.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.controller.helper.CookieHelper;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.dto.request.LoginForm;
import me.programmeris.myresume.api.dto.request.SignUpForm;
import me.programmeris.myresume.api.dto.response.Empty;
import me.programmeris.myresume.api.dto.response.Response;
import me.programmeris.myresume.api.service.UserService;
import me.programmeris.myresume.api.session.Session;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
public class AuthenticationApiController {

    private final UserService userService;

    @PostMapping("/login")
    public Response<Empty> login(HttpServletResponse response,
                                 @RequestBody LoginForm loginForm) {

        log.debug("loginForm {}", loginForm);
        String token = userService.login(loginForm);

        response.addCookie(CookieHelper.createCookie(token));

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("/logout")
    public Response<Empty> logout(@CookieValue(Session.ACCESS_TOKEN_COOKIE_NAME) String token,
                                  HttpServletResponse response) {

        if (StringUtils.isNotEmpty(token)) {
            userService.logout(token);
        }

        response.addCookie(CookieHelper.removeCookie());

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("/signUp")
    public Response<Empty> signUp(@RequestBody SignUpForm signUpForm) {

        userService.signUp(signUpForm);

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("/signUpAndLogin")
    public Response<Empty> signUpAndLogin(@RequestBody SignUpForm signUpForm,
                                          HttpServletResponse response) {

        userService.signUp(signUpForm);

        LoginForm loginForm = new LoginForm();
        loginForm.setId(signUpForm.getEmail());
        loginForm.setPassword(signUpForm.getPassword());
        String token = userService.login(loginForm);

        response.addCookie(CookieHelper.createCookie(token));

        return Response.create(Code.SUCCESS);
    }
}
