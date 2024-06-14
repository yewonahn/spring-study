package com.server.springStudy.apiPayload.code.status;

import com.server.springStudy.apiPayload.code.BaseCode;
import com.server.springStudy.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
// [LomBok] 불필요한 코드와 작업을 줄여주는 라이브러리
// [@AllArgsConstructor] 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성. 클래스의 모든 필드를 한번에 초기화할 수 있음
// [@RequiredArgsConstructor] final 이나 @NonNull 로 선언된 필드만을 파라미터로 받는 생성자 생성. 클래스가 의존하는 필드를 간단하게 초기화할 수 있음
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 필드 밑에서 사용할 enum 작성. 성공 응답에 새로운 코드를 적고 싶다면 enum 추가
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    _CREATED(HttpStatus.CREATED, "COMMON201", "요청 성공 및 리소스 생성됨");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder().code(code).isSuccess(true).message(message).build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(httpStatus)
                .code(code)
                .isSuccess(true)
                .message(message)
                .build();
    }
}
