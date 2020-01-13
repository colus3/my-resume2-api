package me.programmeris.myresume.api.session.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.programmeris.myresume.api.entity.session.AccessToken;
import me.programmeris.myresume.api.service.AccessTokenService;
import me.programmeris.myresume.api.session.Session;
import me.programmeris.myresume.api.tool.CookieUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SessionAspect {

    private final AccessTokenService accessTokenService;

    @Before("@annotation(me.programmeris.myresume.api.session.annotation.Session) && @annotation(target)")
    private void procSession(JoinPoint joinPoint, me.programmeris.myresume.api.session.annotation.Session target) throws Throwable {

        List<Object> args = Arrays.asList(joinPoint.getArgs());
        log.debug("================ SessionAspect getSession s ================");
        HttpServletRequest request = getRequest(args);
        HttpServletResponse response = getResponse(args);

        // 쿠키 값으로 세션 조회
        initSession(request);

        // 세션 체크 후 DB에 저장된 세션 정보를 업데이트
        if (checkSession()) {
            updateSession();
        }


        log.debug("================ SessionAspect getSession e ================");
    }

    private void updateSession() {

        accessTokenService.updateAccessToken(Session.key.get());
        Session.isLoggedIn.set(true);
    }

    private void initSession(HttpServletRequest request) {
        String tokenKey = CookieUtils.getValue(request.getCookies(), Session.ACCESS_TOKEN_COOKIE_NAME);

        if (tokenKey != null) {
            AccessToken accessToken = accessTokenService.getAccessToken(tokenKey);

            Session.key.set(tokenKey);
            Session.user.set(accessToken.getUser());
        }
    }

    private boolean checkSession() {
        // 세션이 없으면 exception
        if (StringUtils.isEmpty(Session.key.get())) {
            Session.isLoggedIn.set(false);
            throw new RuntimeException();
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
