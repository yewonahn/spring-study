package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.domain.entity.Mission;
import com.server.springStudy.domain.mapping.MemberMission;

public class MemberMissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}
