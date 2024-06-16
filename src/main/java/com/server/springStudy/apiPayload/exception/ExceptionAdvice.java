package com.server.springStudy.apiPayload.exception;

import com.server.springStudy.apiPayload.ApiResponse;
import com.server.springStudy.apiPayload.code.ErrorReasonDTO;
import com.server.springStudy.apiPayload.code.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

// build.gradle 에서 validation 의존성 추가

@Slf4j
@RestControllerAdvice(annotations = {RestController.class}) // : 전역적으로 에러를 잡아서 처리 가능하게 하는 역할
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    /** 예외 처리 메서드 : ConstraintViolationException 처리 */
    @ExceptionHandler(ConstraintViolationException.class)
    // 유효성 검사 실패 시 발생하는 예외
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {

        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ERROR : ConstraintViolationException 추출 도중 에러 발생"));

        log.info("예외 처리 : validation");

        // (추출한 메시지를 사용하여 사용자 정의 응답을 생성)
        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY,request);
    }

    /** 예외 처리 메서드 : MethodArgumentNotValidException 처리 */
    // 메소드 파라미터의 유효성 검사 실패 시
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        // 에러 필드와 메세지 추가해주기 위해
        e.getBindingResult().getFieldErrors().stream()
                .forEach(
                        fieldError -> {
                            String fieldName = fieldError.getField();
                            String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                            errors.merge(
                                    fieldName,
                                    errorMessage,
                                    (existingErrorMessage, newErrorMessage) ->
                                            existingErrorMessage + ", " + newErrorMessage);
                        });

        log.info("예외 처리 : handleMethodArgumentNotValid");

        // 수집된 에러 메시지들을 포함하여 사용자 정의 응답 생성
        return handleExceptionInternalArgs(
                e, HttpHeaders.EMPTY, ErrorStatus.valueOf("_BAD_REQUEST"), request, errors);
    }

    /** 예외 처리 메서드 : 모든 종류의 예외 처리 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {

        log.info("예외 처리 : exception");

        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(),request, e.getMessage());
    }

    /** 예외 처리 메서드 : GeneralException 처리 */
    // 사용자 정의 응답 생성
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity onThrowException(GeneralException generalException, HttpServletRequest request) {

        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();

        log.info("예외 처리 : onThrowException");

        return handleExceptionInternal(generalException,errorReasonHttpStatus,null,request);
    }

    /** 예외 처리 메서드 : 응답에 null 넣은 경우 처리 */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errorMessage = "요청 본문을 읽을 수 없습니다. JSON 형식이 올바르지 않습니다.";

        ApiResponse<Object> body = ApiResponse.onFailure(ErrorStatus._BAD_REQUEST.getCode(), errorMessage, null);

        log.info("예외 처리 : handleHttpMessageNotReadable");

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, ErrorStatus._BAD_REQUEST.getHttpStatus(), request);
    }

    /** 내부 헬퍼 메서드 */
    // ApiResponse<Object> 형태 응답 바디 생성

    // ResponseEntityExceptionHandler의 기본 처리 메소드를 호출 (오류 정보를 기반으로 사용자 정의 응답을 생성)
    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDTO reason,
                                                           HttpHeaders headers, HttpServletRequest request) {

        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(),reason.getMessage(),null);

        WebRequest webRequest = new ServletWebRequest(request);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
    }

    // errorPoint : 오류 발생 지점 추가로 포함해서 응답 생성
    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ErrorStatus errorCommonStatus,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(),errorCommonStatus.getMessage(),errorPoint);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }

    // errorArgs : 여러 필드의 유효성 검사 오류 정보를 포함하여 응답 생성
    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers, ErrorStatus errorCommonStatus,
                                                               WebRequest request, Map<String, String> errorArgs) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(),errorCommonStatus.getMessage(),errorArgs);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }

    // 유효성 검사 오류를 처리하는 응답 생성
    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
                                                                     HttpHeaders headers, WebRequest request) {

        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }
}
