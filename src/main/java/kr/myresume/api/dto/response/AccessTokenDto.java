package kr.myresume.api.dto.response;

import kr.myresume.api.entity.session.AccessToken;
import kr.myresume.api.entity.user.User;
import lombok.Getter;
import lombok.Setter;

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
