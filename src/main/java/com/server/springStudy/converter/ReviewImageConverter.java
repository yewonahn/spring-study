package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.ReviewImage;

import java.util.List;

public class ReviewImageConverter {

    public static List<ReviewImage> toReviewImageList(List<String> imageUrlList) {
        return imageUrlList.stream()
                .map(imageUrl ->
                        ReviewImage.builder()
                                .imageUrl(imageUrl)
                                .build()
                        ).toList();
    }

    public static ReviewImage toReviewImage(String imageUrl, Review review) {
        return ReviewImage.builder()
                .imageUrl(imageUrl)
                .review(review)
                .build();
    }
}
