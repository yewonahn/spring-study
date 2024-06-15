package com.server.springStudy.converter;

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
}
