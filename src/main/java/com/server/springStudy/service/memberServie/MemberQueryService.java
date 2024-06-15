package com.server.springStudy.service.memberServie;

public interface MemberQueryService {
    Boolean isMemberMissionAlreadyExists(Long memberId, Long missionId);
}
