package com.server.springStudy.service.storeService;

import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.mapping.MemberMission;
import com.server.springStudy.web.dto.store.MemberMissionCreateRequest;
import com.server.springStudy.web.dto.store.MissionCreateRequest;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, ReviewCreateRequest request, MultipartFile image);

    Mission createMission(Long storeId, MissionCreateRequest request);

    MemberMission createMemberMission(Long memberId, Long storeId, Long missionId, MemberMissionCreateRequest request);
}
