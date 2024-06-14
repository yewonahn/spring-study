package com.server.springStudy.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DTO들은 큰 묶음으로 클래스를 만들고 내부적으로 static 클래스를 만드는 게 좋음
// DTO 자체는 많은 곳에서 사용될 수 있으므로 static class 로 만들면, 매번 class 파일을 만들 필요도 없고, 범용적으로 DTO 를 사용할 수 있음
public class TempResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempTestDTO{
        String testString;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO{
        Integer flag;
    }
}
