package com.server.springStudy.service.memberServie;

import com.server.springStudy.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Boolean isMemberMissionAlreadyExists(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }
}
