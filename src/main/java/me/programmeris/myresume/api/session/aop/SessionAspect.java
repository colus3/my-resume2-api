package me.programmeris.myresume.api.session.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.entity.session.AccessToken;
import me.programmeris.myresume.api.exception.CodedException;
import me.programmeris.myresume.api.service.AccessTokenService;
import me.programmeris.myresume.api.session.Session;
import me.programmeris.myresume.api.tool.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SessionAspect {

    private final AccessTokenService accessTokenService;

    @Before("@annotation(me.programmeris.myresume.api.session.annotation.Session) && @annotation(target)")
    private void preProc(JoinPoint joinPoint, me.programmeris.myresume.api.session.annotation.Session target) throws Throwable {

        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.debug("================ SessionAspect preProc s ================");
        HttpServletRequest request = getRequest(args);

        LocalDateTime now = LocalDateTime.now();

        // 쿠키 값으로 세션 조회
        initSession(request, now);

        // 세션 체크 후 DB에 저장된 세션 정보를 업데이트
        if (checkSession()) {
            updateSession(now);
        }
        log.debug("================ SessionAspect preProc e ================");
    }

    @After("@annotation(me.programmeris.myresume.api.session.annotation.Session)")
    private void postProc() {
        log.debug("================ SessionAspect postProc s ===============");
        clearSession();
        log.debug("================ SessionAspect postProc e ===============");
    }

    private void clearSession() {
        Session.user.set(null);
        Session.token.set("");
        Session.isLoggedIn.set(false);
    }

    private void updateSession(LocalDateTime now) {

        accessTokenService.updateAccessToken(Session.token.get(), now);
        Session.isLoggedIn.set(true);
    }

    private void initSession(HttpServletRequest request, LocalDateTime now) {
        String token = CookieUtils.getValue(request.getCookies(), Session.ACCESS_TOKEN_COOKIE_NAME);

        if (token != null) {
            AccessToken accessToken = accessTokenService.getAccessToken(token, now);

            Session.token.set(token);
            Session.user.set(accessToken.getUser());
        }
    }

    private boolean checkSession() {
        // 세션이 없으면 exception
        if (StringUtils.isEmpty(Session.token.get())) {
            Session.isLoggedIn.set(false);
            throw new CodedException(Code.INVALID_SESSION);
        }
        return true;
    }

    private HttpServletResponse getResponse(List<Object> args) {
        return args.stream().filter(e -> e instanceof HttpServletResponse).map(e -> (HttpServletResponse)e).findFirst().orElse(null);
    }

    private HttpServletRequest getRequest(List<Object> args) {
        return args.stream().filter(e -> e instanceof HttpServletRequest).map(e -> (HttpServletRequest)e).findFirst().orElse(null);
    }
}
