package com.server.springStudy.web.dto.store;

import com.server.springStudy.validation.annotation.ExistMember;
import com.server.springStudy.validation.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;

public record ReviewCreateRequest(
        @ExistStore Long storeId,
        @ExistMember Long memberId,
        Float score,
        @NotBlank String content
) {
}
