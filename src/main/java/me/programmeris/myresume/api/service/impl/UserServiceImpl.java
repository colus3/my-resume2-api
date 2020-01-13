package me.programmeris.myresume.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.response.UserDto;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.repository.UserRepository;
import me.programmeris.myresume.api.service.UserService;
import me.programmeris.myresume.api.session.Session;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    public void addUser(UserDto userDto) {
        User newUser = userDto.to(new User());
        Long createUserId = Session.isLoggedIn.get() ? Session.user.get().getId() : 0;
        newUser.setCreateUserId(createUserId);
        newUser.setUpdateUserId(createUserId);
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void editUser(String email, UserDto userDto) {
        User user = userRepository.findOneByEmail(email);
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUpdateUserId(Session.user.get().getId());
    }
}
