package kr.myresume.api.service.impl;

import kr.myresume.api.exception.CodedException;
import kr.myresume.api.repository.AccessTokenRepository;
import lombok.RequiredArgsConstructor;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.response.AccessTokenDto;
import kr.myresume.api.entity.session.AccessToken;
import kr.myresume.api.service.AccessTokenService;
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
    public AccessTokenDto getAccessToken(String token, LocalDateTime now) {
        AccessToken accessToken = accessTokenRepository.findTopByTokenAndExpiredDtGreaterThanEqualOrderByExpiredDtDesc(token, now);
        AccessTokenDto accessTokenDto = null;
        if (accessToken != null) {
            accessTokenDto = AccessTokenDto.of(accessToken);
        }
        return accessTokenDto;
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
