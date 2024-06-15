package com.server.springStudy.service.MemberServie;

import com.server.springStudy.apiPayload.exception.handler.FoodCategoryHandler;
import com.server.springStudy.converter.MemberConverter;
import com.server.springStudy.converter.MemberPreferConverter;
import com.server.springStudy.domain.entity.FoodCategory;
import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.domain.mapping.MemberPrefer;
import com.server.springStudy.repository.FoodCategoryRepository;
import com.server.springStudy.repository.MemberRepository;
import com.server.springStudy.web.dto.member.MemberJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.FOOD_CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberJoinRequest request) {

        Member newMember = MemberConverter.toMember(request);

        // [유저 선호 카테고리 처리]
        List<FoodCategory> foodCategoryList = request.preferCategory().stream()
                        .map(categoryId -> {
                            return foodCategoryRepository.findById(categoryId).orElseThrow(() -> new FoodCategoryHandler(FOOD_CATEGORY_NOT_FOUND));
                        }).toList();

        // 양방향 연관 관계 설정은 Converter 보단 Service 에서 하는 것이 더 좋음. 따라서 member 안 넣어줌
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        memberRepository.save(newMember);
        return newMember;
    }
}
