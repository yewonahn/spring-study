package com.server.springStudy.service.storeService;

import com.server.springStudy.apiPayload.exception.handler.MemberHandler;
import com.server.springStudy.apiPayload.exception.handler.MissionHandler;
import com.server.springStudy.apiPayload.exception.handler.StoreHandler;
import com.server.springStudy.converter.MemberMissionConverter;
import com.server.springStudy.converter.MissionConverter;
import com.server.springStudy.converter.ReviewConverter;
import com.server.springStudy.converter.ReviewImageConverter;
import com.server.springStudy.domain.entity.*;
import com.server.springStudy.domain.mapping.MemberMission;
import com.server.springStudy.repository.*;
import com.server.springStudy.web.dto.store.MemberMissionCreateRequest;
import com.server.springStudy.web.dto.store.MissionCreateRequest;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, ReviewCreateRequest request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toCreateReview(member, store, request);

        List<ReviewImage> reviewImageList = ReviewImageConverter.toReviewImageList(request.imageUrl());
        log.info("reviewImageList = {}", reviewImageList);
        reviewImageList.forEach(reviewImage -> {reviewImage.setReview(newReview);});

        return reviewRepository.save(newReview);
    }

    @Override
    public Mission createMission(Long storeId, MissionCreateRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(STORE_NOT_FOUND));

        Mission newMission = MissionConverter.toMission(store, request);

        return missionRepository.save(newMission);
    }

    @Override
    public MemberMission createMemberMission(Long memberId, Long storeId, Long missionId, MemberMissionCreateRequest request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(MISSION_NOT_FOUND));

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(member, mission);

        return memberMissionRepository.save(newMemberMission);
    }
}
