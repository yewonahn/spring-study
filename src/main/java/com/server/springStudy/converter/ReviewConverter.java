package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;

public class ReviewConverter {

    public static Review toCreateReview(ReviewCreateRequest request) {

        return Review.builder()
                .score(request.score())
                .content(request.content())
                .build();
    }
}
