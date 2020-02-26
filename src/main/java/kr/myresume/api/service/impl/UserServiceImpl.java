package kr.myresume.api.service.impl;

import kr.myresume.api.exception.CodedException;
import kr.myresume.api.repository.AccessTokenRepository;
import kr.myresume.api.repository.UserRepository;
import kr.myresume.api.session.Session;
import lombok.RequiredArgsConstructor;
import kr.myresume.api.dto.Code;
import kr.myresume.api.dto.request.LoginForm;
import kr.myresume.api.dto.request.SignUpForm;
import kr.myresume.api.dto.response.UserDto;
import kr.myresume.api.entity.session.AccessToken;
import kr.myresume.api.entity.user.User;
import kr.myresume.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Value("${token.expiration_time}")
    private Long tokenExpirationTime;

    private final UserRepository userRepository;
    private final AccessTokenRepository accessTokenRepository;

    @Override
    public UserDto getUser(String email) {
        return UserDto.of(userRepository.findOneByEmail(email));
    }

    @Override
    public UserDto getUser(Long id) {
        return UserDto.of(userRepository.findById(id).orElse(null));
    }

    @Override
    public Page<UserDto> getUsers(String email, Pageable pageable) {
        Page<User> users = null;
        if (StringUtils.isEmpty(email)) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.findAllByEmail(email, pageable);
        }
        return users.map(UserDto::of);
    }

    @Override
    @Transactional
    public void addUser(SignUpForm signUpForm) {
        User newUser = signUpForm.toUser();
        Long createUserId = Session.isLoggedIn.get() ? Session.user.get().getId() : 0;
        newUser.setCreateUserId(createUserId);
        newUser.setUpdateUserId(createUserId);
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void editUser(String email, SignUpForm signUpForm) {
        User user = userRepository.findOneByEmail(email);
        user.setUsername(signUpForm.getUsername());
        user.setAddress(signUpForm.getAddress());
        user.setEmail(signUpForm.getEmail());
        user.setPhone(signUpForm.getPhone());
        user.setUpdateUserId(Session.user.get().getId());
    }

    @Override
    @Transactional
    public String login(LoginForm loginForm) {
        User user = userRepository.findOneByEmail(loginForm.getId());
        if (user == null) {
            throw new CodedException(Code.INCORRECT_USERNAME_OR_PASSWORD);
        }

        if (!StringUtils.equals(user.getPassword(), loginForm.getPassword().get())) {
            throw new CodedException(Code.INCORRECT_USERNAME_OR_PASSWORD);
        }

        AccessToken accessToken = new AccessToken();
        accessToken.setToken(Session.generateToken());
        accessToken.setExpiredDt(LocalDateTime.now().plusMinutes(tokenExpirationTime));
        accessToken.setUser(user);

        accessTokenRepository.save(accessToken);

        return accessToken.getToken();
    }

    @Override
    @Transactional
    public void logout(String token) {

        AccessToken accessToken = accessTokenRepository.findTopByTokenAndExpiredDtGreaterThanEqualOrderByExpiredDtDesc(token, LocalDateTime.now());
        accessTokenRepository.delete(accessToken);
    }

    @Override
    @Transactional
    public void signUp(SignUpForm signUpForm) {

        userRepository.save(signUpForm.toUser());
    }
}
