package com.server.springStudy.service.memberServie;

public interface MemberQueryService {
    Boolean checkMemberMissionExists(Long memberId, Long missionId);

    Boolean checkMemberExists(Long memberId);
}
