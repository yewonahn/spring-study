package com.server.springStudy.service.storeService;

import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.ReviewImage;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;

import java.util.List;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, ReviewCreateRequest request);

    List<ReviewImage> createReviewImage(Review review, ReviewCreateRequest request);
}
