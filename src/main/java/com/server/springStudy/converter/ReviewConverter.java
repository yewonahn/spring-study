package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.Store;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;

import java.util.ArrayList;

public class ReviewConverter {

    public static Review toCreateReview(Member member, Store store, ReviewCreateRequest request) {

        return Review.builder()
                .member(member)
                .store(store)
                .score(request.score())
                .content(request.content())
                .reviewImageList(new ArrayList<>()) // ** 리뷰 엔티티 생성할 때, 빈 리스트로 초기화 해야함 <- eroor : entity.Review.getReviewImageList()" is null
                .build();
    }
}
