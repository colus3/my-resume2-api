package kr.myresume.api.service;

import kr.myresume.api.dto.request.LoginForm;
import kr.myresume.api.dto.request.SignUpForm;
import kr.myresume.api.dto.response.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto getUser(String email);
    UserDto getUser(Long id);
    Page<UserDto> getUsers(String email, Pageable pageable);
    void addUser(SignUpForm signUpForm);
    void editUser(String email, SignUpForm signUpForms);

    String login(LoginForm loginForm);

    void signUp(SignUpForm signUpForm);

    void logout(String token);
}
