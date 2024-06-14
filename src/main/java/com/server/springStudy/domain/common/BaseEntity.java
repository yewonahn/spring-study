package com.server.springStudy.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
 [@MappedSuperclass]
 JPA 에서 공통 필드나 메소드를 정의하는 추상 클래스를 만들기 위해 사용
 독립적인 엔티티는 아니며 이를 상속받는 엔티티 클래스가 필드나 메소드를 공유하게 됨
 */
@MappedSuperclass
/*
[@EntityListeners]
JPA 엔티티의 생명주기 이벤트를 감지하여 감사 정보를 자동으로 설정하거나, 특정 로직을 실행하기 위해 사용됨
(엔티티 감사 정보 : 데이터베이스 엔티티 객체에 대한 생성, 수정 등의 이력을 추적하기 위한 메타데이터)
[AuditingEntityListener]
Spring Data JPA에서 제공. 엔티티의 생성일자와 수정일자를 자동으로 설정하는 데 주로 사용
 */
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;


}
