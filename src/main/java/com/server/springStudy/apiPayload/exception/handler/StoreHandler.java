package com.server.springStudy.apiPayload.exception.handler;

import com.server.springStudy.apiPayload.code.BaseErrorCode;
import com.server.springStudy.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
