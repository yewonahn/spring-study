package com.server.springStudy.web.dto.store;

import com.server.springStudy.validation.annotation.ExistMember;
import com.server.springStudy.validation.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ReviewCreateRequest(
        @ExistStore Long storeId,
        @ExistMember Long memberId,
        Float score,
        @NotBlank String content,
        List<String> imageUrl
) {
}
