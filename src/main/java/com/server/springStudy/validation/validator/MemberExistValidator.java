package com.server.springStudy.validation.validator;

import com.server.springStudy.service.memberServie.MemberQueryService;
import com.server.springStudy.validation.annotation.ExistMember;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMember, Long>  {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long memberId, ConstraintValidatorContext context) {

        Boolean isMemberExist = memberQueryService.checkMemberExists(memberId);

        if (!isMemberExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isMemberExist;
    }
}
