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
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SessionAspect {

    private final AccessTokenService accessTokenService;

    @Around("execution(* me.programmeris..controller.*.*(..)) && @annotation(me.programmeris.myresume.api.session.annotation.Session)")
    private Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        log.debug("================ SessionAspect process s ================");
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();

        LocalDateTime now = LocalDateTime.now();

        // 쿠키 값으로 세션 초기화 및 체크
        if (!initSession(request, now) || !checkSession()) {
            throw new CodedException(Code.INVALID_SESSION);
        }

        // DB에 저장된 세션 정보를 업데이트
        updateSession(now);

        Object result = joinPoint.proceed();

        clearSession();

        log.debug("================ SessionAspect process e ================");
        return result;
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

    private boolean initSession(HttpServletRequest request, LocalDateTime now) {
        String token = CookieUtils.getValue(request.getCookies(), Session.ACCESS_TOKEN_COOKIE_NAME);

        if (token == null) return false;

        AccessToken accessToken = accessTokenService.getAccessToken(token, now);
        if (accessToken == null) return false;

        Session.token.set(token);
        Session.user.set(accessToken.getUser());

        return true;
    }

    private boolean checkSession() {
        // 세션이 없으면 exception
        if (StringUtils.isEmpty(Session.token.get())) {
            Session.isLoggedIn.set(false);
            throw new CodedException(Code.INVALID_SESSION);
        }
        return true;
    }

    private HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
