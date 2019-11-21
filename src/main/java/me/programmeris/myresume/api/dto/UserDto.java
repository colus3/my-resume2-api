package me.programmeris.myresume.api.dto;

import lombok.Getter;

@Getter
public class UserDto {

    private String username;
    private String phone;
    private String email;

    public UserDto(String username, String phone, String email) {
        this.username = username;
        this.phone = phone;
        this.email = email;
    }
}
