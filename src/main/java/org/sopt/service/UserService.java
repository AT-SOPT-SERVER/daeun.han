package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.UserCreateRequest;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long saveUser(UserCreateRequest userCreateRequest) {
        User user = new User(userCreateRequest.name());
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
