package com.server.springStudy.web.controller;

import com.server.springStudy.apiPayload.ApiResponse;
import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.mapping.MemberMission;
import com.server.springStudy.service.storeService.StoreCommandService;
import com.server.springStudy.validation.annotation.ExistMember;
import com.server.springStudy.validation.annotation.ExistStore;
import com.server.springStudy.web.dto.store.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewCreateResponse> createReview(
            @RequestBody @Valid ReviewCreateRequest request,
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @ExistMember @RequestParam(name = "memberId")  Long memberId) {

        Review newReview = storeCommandService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(ReviewCreateResponse.of(newReview));
    }

    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionCreateResponse> createMission(
            @RequestBody @Valid MissionCreateRequest request,
            @ExistStore @PathVariable(name = "storeId") Long storeId) {

        Mission newMission = storeCommandService.createMission(storeId, request);

        return ApiResponse.onSuccess(new MissionCreateResponse(newMission.getId()));
    }

    @PostMapping("/{storeId}/mission/{missionId}")
    public ApiResponse<MemberMissionCreateResponse> createMemberMission(
            @RequestBody @Valid MemberMissionCreateRequest request,
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @PathVariable(name = "missionId") Long missionId,
            @ExistMember @RequestParam(name = "memberId") Long memberId) {

        MemberMission newMemberMission = storeCommandService.createMemberMission(memberId, storeId, missionId, request);

        return ApiResponse.onSuccess(new MemberMissionCreateResponse(newMemberMission.getId()));
    }
}
