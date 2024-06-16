package com.server.springStudy.web.dto.store;

import com.server.springStudy.validation.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record MissionCreateRequest(
        @ExistStore Long storeId,
        @NotBlank String missionSpec,
        Integer point,
        LocalDate deadline
) {
}
