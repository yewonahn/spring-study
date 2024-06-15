package com.server.springStudy.web.dto.store;

import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.ReviewImage;

import java.util.List;

public record ReviewCreateResponse(
        Long reviewId,
        List<Long> reviewImageIdList
) {
    public static ReviewCreateResponse of(Review review) {
        return new ReviewCreateResponse(
                review.getId(),
                review.getReviewImageList().stream().map(ReviewImage::getId).toList()
        );
    }
}
