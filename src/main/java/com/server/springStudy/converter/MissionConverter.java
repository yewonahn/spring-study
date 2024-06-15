package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.domain.entity.Store;
import com.server.springStudy.web.dto.store.MissionCreateRequest;

public class MissionConverter {
    public static Mission toMission(Store store, MissionCreateRequest request) {
        return Mission.builder()
                .missionSpec(request.missionSpec())
                .point(request.point())
                .deadline(request.deadline())
                .store(store)
                .build();
    }
}
