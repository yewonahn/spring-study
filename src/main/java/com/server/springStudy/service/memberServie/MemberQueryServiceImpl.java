package com.server.springStudy.service.memberServie;

import com.server.springStudy.repository.MemberMissionRepository;
import com.server.springStudy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public Boolean checkMemberMissionExists(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }

    @Override
    public Boolean checkMemberExists(Long memberId) {
        return memberRepository.existsById(memberId);
    }
}
