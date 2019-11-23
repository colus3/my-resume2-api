package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.response.UserDto;

public interface UserService {

    UserDto getUser(String email);
    UserDto getUser(Long id);
}
