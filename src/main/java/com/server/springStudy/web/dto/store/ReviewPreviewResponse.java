package com.server.springStudy.web.dto.store;

import java.time.LocalDateTime;

public record ReviewPreviewResponse(
        String ownerNickName,
        Float score,
        String content,
        LocalDateTime createdAt
) {
}
