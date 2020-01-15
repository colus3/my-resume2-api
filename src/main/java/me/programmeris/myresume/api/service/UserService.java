package me.programmeris.myresume.api.service;

import me.programmeris.myresume.api.dto.request.LoginForm;
import me.programmeris.myresume.api.dto.request.SignUpForm;
import me.programmeris.myresume.api.dto.response.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto getUser(String email);
    UserDto getUser(Long id);
    Page<UserDto> getUsers(String email, Pageable pageable);
    void addUser(UserDto userDto);
    void editUser(String email, UserDto userDto);

    String login(LoginForm loginForm);

    void signUp(SignUpForm signUpForm);

    void logout(String token);
}
