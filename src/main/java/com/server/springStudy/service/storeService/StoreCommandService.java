package com.server.springStudy.service.storeService;

import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.web.dto.store.MissionCreateRequest;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, ReviewCreateRequest request);

    Mission createMission(Long storeId, MissionCreateRequest request);
}
