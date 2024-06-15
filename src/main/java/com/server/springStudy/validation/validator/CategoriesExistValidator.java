package com.server.springStudy.validation.validator;

import com.server.springStudy.repository.FoodCategoryRepository;
import com.server.springStudy.validation.annotation.ExistCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.FOOD_CATEGORY_NOT_FOUND;

@Component
@RequiredArgsConstructor
// ExistCategories 에 대한 로직을 담을 것이며 검증 대상이 List<Long> 임을 명시
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryRepository foodCategoryRepository;

    // 1. 메소드 두개 다 오버라이딩 한다고 선택 후
    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // 2. isValid 메소드만 원하는 형태로 바꿔주면 됨
    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {

        boolean isValid = values.stream()
                .allMatch(foodCategoryRepository::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
