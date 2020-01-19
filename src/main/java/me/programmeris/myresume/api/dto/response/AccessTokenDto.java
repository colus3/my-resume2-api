package me.programmeris.myresume.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import me.programmeris.myresume.api.entity.session.AccessToken;
import me.programmeris.myresume.api.entity.user.User;

@Getter @Setter
public class AccessTokenDto implements ResponseData {

    private String token;
    private UserDto user;

    public AccessTokenDto(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }

    public AccessTokenDto(String token, User user) {
        this.token = token;
        this.user = UserDto.of(user);
    }

    public static AccessTokenDto of(AccessToken accessToken) {
        if (accessToken == null) return null;

        return new AccessTokenDto(accessToken.getToken(), accessToken.getUser());
    }
}
