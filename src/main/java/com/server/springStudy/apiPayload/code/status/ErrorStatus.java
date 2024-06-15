package com.server.springStudy.apiPayload.code.status;

import com.server.springStudy.apiPayload.code.BaseErrorCode;
import com.server.springStudy.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
[Enum 관리에 대한 고찰]
: 프론트 개발자 입장에서 API 응답의 원인을 정확하게 파악할 수 있도록 code 에도 체계적인 규칙을 정해두자

1. common 에러는 COMMON000 으로 둔다 <- 잘 안쓰지만 마땅하지 않을 때 사용
2. 관련된 경우마다 code에 명시적으로 표현한다
	: ex. 멤버 관련이면 MEMBER001, 게시글 관련이면 ARTICLE001 이런 식으로
3. 2번에 이어서 4000번대를 붙인다. 서버측 잘못은 그냥 COMMON 에러의 서버 에러를 쓰면 됨
	: MEMBER400_1 아니면 MEMBER4001 이런 식으로
 */

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 개발 과정에서 해당 프로젝트에서 필요한 예외 상황에 대한 enum 추가

    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 에러 테스트
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "에러 테스트"),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 음식 카테고리 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "해당하는 음식 카테고리가 없습니다."),

    // 가게 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "가게를 찾을 수 없습니다."),

    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당하는 미션을 찾을 수 없습니다."),

    // 멤버 미션 관련 에러
    IS_ALREADY_ON_GOING(HttpStatus.BAD_REQUEST, "MEMBER_MISSION4002", "회원이 이미 진행 중인 미션 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
