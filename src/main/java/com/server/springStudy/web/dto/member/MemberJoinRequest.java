package com.server.springStudy.web.dto.member;

import com.server.springStudy.domain.enums.Gender;

import java.util.List;

public record MemberJoinRequest(
        String name,
        Gender gender,
        String address,
        String specAddress,
        List<Long> preferCategory
) {
}
