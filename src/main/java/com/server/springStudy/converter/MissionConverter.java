package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.web.dto.store.MissionCreateRequest;

public class MissionConverter {
    public static Mission toMission(MissionCreateRequest request) {
        return Mission.builder()
                .missionSpec(request.missionSpec())
                .point(request.point())
                .deadline(request.deadline())
                .build();
    }
}
