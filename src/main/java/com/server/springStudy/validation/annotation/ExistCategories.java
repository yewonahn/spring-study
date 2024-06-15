package com.server.springStudy.validation.annotation;

import com.server.springStudy.validation.validator.CategoriesExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션 만들 때 사용
@Constraint(validatedBy = CategoriesExistValidator.class)   // 사용자가 validation을 커스텀 어노테이션을 통해 할 수 있도록 자바에서 제공하는 어노테이션
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })  // 어노테이션의 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명 주기 지정 (RUNTIME = 실행 하는 동안에만 유효)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}