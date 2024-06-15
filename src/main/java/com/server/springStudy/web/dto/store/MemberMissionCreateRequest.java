package com.server.springStudy.web.dto.store;

public record MemberMissionCreateRequest(
        Long memberId,
        Long storeId,
        Long missionId
) {
}
