package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.Store;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;

public class ReviewConverter {

    public static Review toCreateReview(Member member, Store store, ReviewCreateRequest request) {

        return Review.builder()
                .member(member)
                .store(store)
                .score(request.score())
                .content(request.content())
                .build();
    }
}
