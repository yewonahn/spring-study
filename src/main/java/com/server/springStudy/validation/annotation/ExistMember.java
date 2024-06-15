package com.server.springStudy.validation.annotation;

import com.server.springStudy.validation.validator.MemberExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMember {
    String message() default "존재하지 않는 회원 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
