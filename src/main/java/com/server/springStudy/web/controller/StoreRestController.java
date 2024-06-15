package com.server.springStudy.web.controller;

import com.server.springStudy.apiPayload.ApiResponse;
import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.service.storeService.StoreCommandService;
import com.server.springStudy.web.dto.store.MissionCreateRequest;
import com.server.springStudy.web.dto.store.MissionCreateResponse;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;
import com.server.springStudy.web.dto.store.ReviewCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewCreateResponse> createReview(
            @RequestBody @Valid ReviewCreateRequest request,
            @PathVariable Long storeId,
            @RequestHeader Long memberId) {

        log.info("reviewImageList = {}", request.imageUrl());

        Review newReview = storeCommandService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(ReviewCreateResponse.of(newReview));
    }

    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionCreateResponse> createMission(
            @RequestBody @Valid MissionCreateRequest request,
            @PathVariable Long storeId
    ) {
        Mission newMission = storeCommandService.createMission(storeId, request);

        return ApiResponse.onSuccess(new MissionCreateResponse(newMission.getId()));
    }
}
