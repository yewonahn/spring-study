package com.server.springStudy.web.dto.store;

import com.server.springStudy.validation.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.util.List;

public record ReviewCreateRequest(
        @ExistStore Long storeId,
        @NonNull Long memberId,
        @NonNull Float score,
        @NotBlank String content,
        List<String> imageUrl
) {
}
