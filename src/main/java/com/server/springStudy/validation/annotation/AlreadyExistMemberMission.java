package com.server.springStudy.validation.annotation;

import com.server.springStudy.validation.validator.MemberMissionExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberMissionExistValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyExistMemberMission {

    String message() default "회원이 이미 진행 중인 미션 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
