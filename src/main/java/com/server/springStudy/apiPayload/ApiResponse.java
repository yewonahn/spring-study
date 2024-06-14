package com.server.springStudy.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.server.springStudy.apiPayload.code.BaseCode;
import com.server.springStudy.apiPayload.code.status.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// [@JsonPropertyOrder] 직렬화에서 property 의 순서 지정 가능 (직렬화 : 자바 객체를 json 형태로 변환)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
// [ApiResponse] 성공, 실패할 시 리턴할 함수들과 필드들 작성
// 모든 코드를 ApiResponse 로 리턴함으로써 응답 통일하여 프론트와의 통신을 용이하게 함
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;    // 성공 여부
    private final String code;  // HTTP 상태코드만으로는 제한적인 정보만 줄 수 있으므로, 더 세부적인 응답 상황을 알려주기 위한 필드
    private final String message;   // code에 추가적으로 우리에게 익숙한 문자로 상황을 알려주는 필드
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;   // 실제로 클라이언트에게 필요한 데이터가 딤김. 어떤 형태의 값이 올지 모르므로 Generic 으로 설정
    // 에러 상황에서는 보통 null. null 을 담지 않는 에러 상황도 있음

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}
