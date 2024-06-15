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

        // 커스텀 Validator 에서 에러 메시지 설정하기 위해 사용
        if (!isValid) {
            // 유효성 검증에 실패하면 기본적으로 @Constraint 애노테이션의 message 속성에 지정된 기본 메시지가 사용됨
            // 커스텀 메시지 설정하고 싶은 경우 이 기본 메시지 비활성화
            context.disableDefaultConstraintViolation();    // : 기본적으로 제공되는 ConstraintViolation 메시지 비활성화
            context.buildConstraintViolationWithTemplate(FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();  // 새로운 ConstraintViolation 메시지 빌드하고 추가하는 역할
        }

        return isValid;
    }
}
