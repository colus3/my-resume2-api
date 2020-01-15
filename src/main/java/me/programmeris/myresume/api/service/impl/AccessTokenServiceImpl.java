package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Code;
import me.programmeris.myresume.api.entity.session.AccessToken;
import me.programmeris.myresume.api.exception.CodedException;
import me.programmeris.myresume.api.repository.AccessTokenRepository;
import me.programmeris.myresume.api.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("${token.expiration_time}")
    private Long tokenExpirationTime;

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public AccessToken getAccessToken(String token, LocalDateTime now) {
        AccessToken accessToken = accessTokenRepository.findTopByTokenAndExpiredDtGreaterThanEqualOrderByExpiredDtDesc(token, now);
        accessToken.getUser();
        return accessToken;
    }

    @Override
    @Transactional
    public void updateAccessToken(String token, LocalDateTime now) {

        AccessToken accessToken = accessTokenRepository.findTopByTokenAndExpiredDtGreaterThanEqualOrderByExpiredDtDesc(token, now);

        if (accessToken == null) {
            throw new CodedException(Code.NOT_LOGGED_IN);
        }
        accessToken.setExpiredDt(now.plusMinutes(tokenExpirationTime));
    }
}
