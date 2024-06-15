package com.server.springStudy.apiPayload.exception.handler;

import com.server.springStudy.apiPayload.code.BaseErrorCode;
import com.server.springStudy.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
