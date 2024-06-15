package com.server.springStudy.web.dto.store;

import java.time.LocalDate;

public record MissionCreateRequest(
        // [check] storeId 도 받는지
        String missionSpec,
        Integer point,
        LocalDate deadline

) {
}
