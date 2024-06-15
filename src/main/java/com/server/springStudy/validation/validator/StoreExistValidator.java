package com.server.springStudy.validation.validator;

import com.server.springStudy.service.storeService.StoreQueryService;
import com.server.springStudy.validation.annotation.ExistStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.STORE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {

        Boolean isStoreExist = storeQueryService.isStoreExist(storeId);

        if (!isStoreExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isStoreExist;
    }
}
