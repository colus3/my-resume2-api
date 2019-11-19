package me.programmeris.myresume.api.service;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.entity.User;
import me.programmeris.myresume.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String email) {

        return userRepository.findByEmail(email);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
