package kr.myresume.api.service;

import kr.myresume.api.dto.response.AccessTokenDto;

import java.time.LocalDateTime;

public interface AccessTokenService {

    AccessTokenDto getAccessToken(String token, LocalDateTime now);

    void updateAccessToken(String token, LocalDateTime now);

}
