package com.server.springStudy.domain.entity;

import com.server.springStudy.domain.common.BaseEntity;
import com.server.springStudy.domain.enums.Gender;
import com.server.springStudy.domain.enums.MemberStatus;
import com.server.springStudy.domain.enums.SocialType;
import com.server.springStudy.domain.mapping.MemberAgree;
import com.server.springStudy.domain.mapping.MemberMission;
import com.server.springStudy.domain.mapping.MemberPrefer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    private String email;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    private Integer point;

    private LocalDate inactiveDate;

    // 기본 값인 ORDINAL을 사용하면 데이터베이스에 enum의 순서가 저장됨
    // 만약 Springboot에서 enum의 순서를 바꾸게 될 경우 에러가 생김
    // 반드시 STRING 사용할 것
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}