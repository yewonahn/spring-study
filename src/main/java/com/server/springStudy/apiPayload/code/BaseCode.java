package com.server.springStudy.apiPayload.code;

// [BaseCode] 인터페이스 BaseCode 를 구체화 하는 Status 에서 아래 두 개의 메소드를 반드시 Override 할 것을 강제
public interface BaseCode {
    public ReasonDTO getReason();

    public ReasonDTO getReasonHttpStatus();
}
