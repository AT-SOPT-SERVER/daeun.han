package org.sopt.dto;

public record UserCreateRequest(String name, String email) {
    // 생성자, getter가 자동 생성됨
}

