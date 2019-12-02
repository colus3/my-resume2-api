package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.response.UserDto;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.repository.UserRepository;
import me.programmeris.myresume.api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        return userRepository.findAllByEmail(email, pageable).map(UserDto::of);
    }
}
