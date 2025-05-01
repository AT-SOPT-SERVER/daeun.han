package org.sopt.global;

import org.sopt.dto.ErrorResponse;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice는 전역적으로 예외를 처리-> 모든 @RestController에서 발생하는 예외를 한 곳에서 처리 가능
// @ExceptionHandler 메서드와 함께 사용되어 일관된 에러 응답을 제공
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 정의한 CustomException 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getStatusCode(),
                errorCode.getMessage()
        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    // 기타 예상하지 못한 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
        );
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(errorResponse);
    }
}