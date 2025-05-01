package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.dto.UserCreateRequest;
import org.sopt.service.PostService;
import org.sopt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 회원가입
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest){
        userService.saveUser(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }
}
