package com.server.springStudy.converter;

import com.server.springStudy.domain.entity.FoodCategory;
import com.server.springStudy.domain.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    // 양방향 연관 관계 설정은 Converter 보단 Service 에서 하는 것이 더 좋음 (단방향은 컨버터에서 설정해도 괜찮음)
    public static List<MemberPrefer> toMemberPreferList (List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).toList();
    }
}
