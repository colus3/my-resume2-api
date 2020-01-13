package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.entity.session.AccessToken;

public interface AccessTokenService {

    AccessToken getAccessToken(String key);

    void updateAccessToken(String key);

}
