package com.server.springStudy.service.memberServie;

import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.web.dto.member.MemberJoinRequest;

public interface MemberCommandService {
    Member joinMember(MemberJoinRequest request);
}
