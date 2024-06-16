package com.server.springStudy.web.dto.store;

import com.server.springStudy.validation.annotation.AlreadyExistMemberMission;
import com.server.springStudy.validation.annotation.ExistMember;
import com.server.springStudy.validation.annotation.ExistStore;

@AlreadyExistMemberMission
public record MemberMissionCreateRequest(
        @ExistMember Long memberId,
        @ExistStore Long storeId,
        Long missionId
) {
}
