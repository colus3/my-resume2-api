package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.response.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto getUser(String email);
    UserDto getUser(Long id);
    Page<UserDto> getUsers(String email, Pageable pageable);
    void insertUser(UserDto userDto);
}
