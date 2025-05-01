package org.sopt.controller;

import org.sopt.dto.UserCreateRequest;
import org.sopt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 회원가입
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        Long userId = userService.saveUser(userCreateRequest);
        return ResponseEntity.status(201).body(userId);
    }
}
