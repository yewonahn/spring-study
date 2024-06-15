package com.server.springStudy.apiPayload.exception.handler;

import com.server.springStudy.apiPayload.code.BaseErrorCode;
import com.server.springStudy.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
