package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.UserDto;

public interface UserService {

    UserDto getUser(String email);
    UserDto getUser(Long id);
}
