package kr.myresume.api.controller;

import kr.myresume.api.controller.helper.CookieHelper;
import kr.myresume.api.session.annotation.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.request.LoginForm;
import kr.myresume.api.dto.request.SignUpForm;
import kr.myresume.api.dto.response.AccessTokenDto;
import kr.myresume.api.dto.response.Empty;
import kr.myresume.api.dto.response.Response;
import kr.myresume.api.service.AccessTokenService;
import kr.myresume.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
public class AuthenticationApiController {

    private final UserService userService;
    private final AccessTokenService accessTokenService;

    @PostMapping("login")
    public Response<AccessTokenDto> login(HttpServletResponse response,
                                 @RequestBody LoginForm loginForm) {

        log.debug("loginForm {}", loginForm);
        String token = userService.login(loginForm);

        AccessTokenDto accessTokenDto = accessTokenService.getAccessToken(token, LocalDateTime.now());

        response.addCookie(CookieHelper.createCookie(token));

        return Response.create(Code.SUCCESS, accessTokenDto);
    }

    @PostMapping("logout")
    public Response<Empty> logout(@CookieValue(kr.myresume.api.session.Session.COOKIE_NAME) String token,
                                  HttpServletResponse response) {

        if (StringUtils.isNotEmpty(token)) {
            userService.logout(token);
        }

        response.addCookie(CookieHelper.removeCookie());

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("sign-up")
    public Response<Empty> signUp(@RequestBody SignUpForm signUpForm) {

        userService.signUp(signUpForm);

        return Response.create(Code.SUCCESS);
    }

    @PostMapping("sign-up-and-login")
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

    @Session
    @GetMapping("me")
    public Response<AccessTokenDto> getSessionInfo(@CookieValue(kr.myresume.api.session.Session.COOKIE_NAME) String token) {

        AccessTokenDto accessTokenDto = accessTokenService.getAccessToken(token, LocalDateTime.now());

        return Response.create(Code.SUCCESS, accessTokenDto);
    }
}
