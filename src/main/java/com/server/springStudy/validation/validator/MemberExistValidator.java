package com.server.springStudy.validation.validator;

import com.server.springStudy.service.memberServie.MemberQueryService;
import com.server.springStudy.validation.annotation.ExistMember;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;

@Slf4j
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
            log.warn("유효성 검사 : MEMBER_NOT_FOUND");
        }

        return isMemberExist;
    }
}
