package org.sopt.global;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 게시글 관련
    TITLE_NULL(HttpStatus.BAD_REQUEST, "제목은 필수입니다."),
    CONTENT_NULL(HttpStatus.BAD_REQUEST, "내용은 필수입니다."),
    TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, "제목은 30자를 넘을 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),
    UNAUTHORIZED_POST_ACCESS(HttpStatus.FORBIDDEN, "게시글에 대한 권한이 없습니다."),

    // 사용자 관련
    USER_NAME_NULL(HttpStatus.BAD_REQUEST, "사용자 이름은 필수입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."),
    UNAUTHORIZED_USER_ACCESS(HttpStatus.FORBIDDEN, "사용자 정보 접근 권한이 없습니다."),

    // 공통
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }
}