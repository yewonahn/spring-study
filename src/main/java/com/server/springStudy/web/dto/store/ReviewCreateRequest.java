package com.server.springStudy.web.dto.store;

import java.util.List;

public record ReviewCreateRequest(
        Float score,
        String content,
        List<String> imageUrl
) {
}
