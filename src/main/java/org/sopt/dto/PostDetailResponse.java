package org.sopt.dto;

public record PostDetailResponse(
        Long id,
        String title,
        String content,
        String authorName
) {}
