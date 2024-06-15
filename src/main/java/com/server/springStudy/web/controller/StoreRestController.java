package com.server.springStudy.web.controller;

import com.server.springStudy.apiPayload.ApiResponse;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.ReviewImage;
import com.server.springStudy.service.storeService.StoreCommandService;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;
import com.server.springStudy.web.dto.store.ReviewCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewCreateResponse> createReview(
            @RequestBody @Valid ReviewCreateRequest request,
            @PathVariable Long storeId,
            @RequestHeader Long memberId
            ) {

        Review newReview = storeCommandService.createReview(memberId, storeId, request);
        List<ReviewImage> reviewImageList = storeCommandService.createReviewImage(newReview, request);

        return ApiResponse.onSuccess(ReviewCreateResponse.from(newReview.getId(), reviewImageList));
    }
}
