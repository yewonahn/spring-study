package com.server.springStudy.web.dto.store;

import java.util.List;

public record ReviewPreviewListResponse(
        List<ReviewPreviewResponse> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
) {
}
