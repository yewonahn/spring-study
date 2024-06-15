package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.web.dto.member.MemberJoinRequest;

import java.util.ArrayList;

public class MemberConverter {

    public static Member toMember(MemberJoinRequest request) {

        return Member.builder()
                .address(request.address())
                .specAddress(request.specAddress())
                .gender(request.gender())
                .name(request.name())
                .memberPreferList(new ArrayList<>())
                .build();
    }

}
