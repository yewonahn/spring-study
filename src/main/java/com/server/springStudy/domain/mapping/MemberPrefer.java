package com.server.springStudy.domain.mapping;

import com.server.springStudy.domain.common.BaseEntity;
import com.server.springStudy.domain.entity.FoodCategory;
import com.server.springStudy.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    // 연관 관계 편의 매서드
    public void setMember (Member member) {
        if(this.member != null)
            member.getMemberPreferList().remove(this);
        this.member = member;
        member.getMemberPreferList().add(this);
    }
}