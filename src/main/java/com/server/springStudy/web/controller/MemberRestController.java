package com.server.springStudy.web.controller;

import com.server.springStudy.apiPayload.ApiResponse;
import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.service.memberServie.MemberCommandService;
import com.server.springStudy.web.dto.member.MemberJoinRequest;
import com.server.springStudy.web.dto.member.MemberJoinResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public ApiResponse<MemberJoinResponse> join(@RequestBody @Valid MemberJoinRequest request) {

        Member newMember = memberCommandService.joinMember(request);

        return ApiResponse.onSuccess(MemberJoinResponse.of(newMember));
    }
}
