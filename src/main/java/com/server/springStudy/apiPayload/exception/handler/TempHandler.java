package com.server.springStudy.apiPayload.exception.handler;

import com.server.springStudy.apiPayload.code.BaseErrorCode;
import com.server.springStudy.apiPayload.exception.GeneralException;

/*
[handler]
: handler 패키지 안에 에러와 관련한 handler 클래스들을 생성

    (ex)
    user 관련 handler : UserHandler
    post 관련 handler : PostHandler
 */

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {

        // super : 부모 클래스의 생성자 호출
        super(errorCode);
    }
}
