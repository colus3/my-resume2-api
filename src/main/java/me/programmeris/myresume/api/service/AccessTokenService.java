package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.entity.session.AccessToken;

import java.time.LocalDateTime;

public interface AccessTokenService {

    AccessToken getAccessToken(String key, LocalDateTime now);

    void updateAccessToken(String key, LocalDateTime now);

}
