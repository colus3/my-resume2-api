package kr.myresume.api.session.aop;

import kr.myresume.api.exception.CodedException;
import kr.myresume.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.response.AccessTokenDto;
import kr.myresume.api.entity.user.User;
import kr.myresume.api.service.AccessTokenService;
import kr.myresume.api.session.Session;
import kr.myresume.api.tool.CookieUtils;
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
    private final UserRepository userRepository;

    @Around("execution(* me.programmeris..controller.*.*(..)) && @annotation(kr.myresume.api.session.annotation.Session)")
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
        String token = CookieUtils.getValue(request.getCookies(), Session.COOKIE_NAME);

        if (token == null) return false;

        AccessTokenDto accessToken = accessTokenService.getAccessToken(token, now);
        if (accessToken == null) return false;

        User user = userRepository.findOneByEmail(accessToken.getUser().getEmail());

        Session.token.set(token);
        Session.user.set(user);

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
