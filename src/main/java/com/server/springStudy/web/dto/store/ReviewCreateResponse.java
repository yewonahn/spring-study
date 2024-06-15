package com.server.springStudy.web.dto.store;

import com.server.springStudy.domain.entity.ReviewImage;

import java.util.List;

public record ReviewCreateResponse(
        Long reviewId,
        List<Long> reviewImageIdList
) {
    public static ReviewCreateResponse from(Long reviewId, List<ReviewImage> reviewImageIdList) {
        return new ReviewCreateResponse(
                reviewId,
                reviewImageIdList.stream().map(ReviewImage::getId).toList()
        );
    }
}
