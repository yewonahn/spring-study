package com.server.springStudy.apiPayload.exception.handler;

import com.server.springStudy.apiPayload.code.BaseErrorCode;
import com.server.springStudy.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {

    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}