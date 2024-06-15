package com.server.springStudy.validation.validator;

import com.server.springStudy.repository.MemberMissionRepository;
import com.server.springStudy.validation.annotation.AlreadyExistMemberMission;
import com.server.springStudy.web.dto.store.MemberMissionCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.IS_ALREADY_ON_GOING;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<AlreadyExistMemberMission, MemberMissionCreateRequest> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(AlreadyExistMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionCreateRequest request, ConstraintValidatorContext context) {

        boolean isAlreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionId(request.memberId(), request.missionId());

        if (isAlreadyChallenging) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(IS_ALREADY_ON_GOING.toString()).addConstraintViolation();
        }

        return !isAlreadyChallenging;
    }
}
