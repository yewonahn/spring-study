package com.server.springStudy.web.dto.store;

import com.server.springStudy.validation.annotation.AlreadyExistMemberMission;
import org.springframework.lang.NonNull;

@AlreadyExistMemberMission
public record MemberMissionCreateRequest(
        @NonNull Long memberId,
        @NonNull Long storeId,
        @NonNull Long missionId
) {
}
