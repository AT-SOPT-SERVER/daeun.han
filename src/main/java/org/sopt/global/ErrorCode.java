package org.sopt.global;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_TITLE(HttpStatus.BAD_REQUEST, "제목은 필수이며, 30자를 넘을 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 게시물을 찾을 수 없습니다."),
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
