package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.entity.session.AccessToken;
import me.programmeris.myresume.api.repository.AccessTokenRepository;
import me.programmeris.myresume.api.service.AccessTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccessTokenServiceImpl implements AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public AccessToken getAccessToken(String key) {
        AccessToken accessToken = accessTokenRepository.findTopByKeyAndExpiredDTGreaterThanEqualOrderByExpiredDTDesc(key, LocalDateTime.now());
        accessToken.getUser();
        return accessToken;
    }

    @Override
    @Transactional
    public void updateAccessToken(String key) {

        LocalDateTime now = LocalDateTime.now();
        AccessToken accessToken = accessTokenRepository.findTopByKeyAndExpiredDTGreaterThanEqualOrderByExpiredDTDesc(key, now);

        if (accessToken == null) {
            throw new RuntimeException();
        }
        accessToken.setExpiredDT(now);
    }
}
