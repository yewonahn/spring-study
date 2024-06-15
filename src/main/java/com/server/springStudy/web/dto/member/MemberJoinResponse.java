package com.server.springStudy.web.dto.member;

import com.server.springStudy.domain.entity.Member;

import java.time.LocalDateTime;

public record MemberJoinResponse(
        Long memberId,
        LocalDateTime createdAt
) {
    public static MemberJoinResponse of (Member member) {
        return new MemberJoinResponse(
                member.getId(),
                LocalDateTime.now()
        );
    }
}
