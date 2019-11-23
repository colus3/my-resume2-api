package me.programmeris.myresume.api.dto.response;

import lombok.Getter;
import me.programmeris.myresume.api.entity.user.User;

@Getter
public class UserDto implements ResponseData {

    private String username;
    private String phone;
    private String email;

    public UserDto(String username, String phone, String email) {
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public static UserDto of(User user) {
        if (user == null) return null;

        return new UserDto(user.getUsername(), user.getPhone(), user.getEmail());
    }

}
