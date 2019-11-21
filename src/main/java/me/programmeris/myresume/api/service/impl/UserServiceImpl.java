package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.UserDto;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.repository.UserRepository;
import me.programmeris.myresume.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null || user.getId() == null) return null;
        return new UserDto(user.getUsername(), user.getPhone(), user.getEmail());
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null || user.getId() == null) return null;
        return new UserDto(user.getUsername(), user.getPhone(), user.getEmail());
    }
}
