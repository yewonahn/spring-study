package com.server.springStudy.web.dto.member;

import com.server.springStudy.domain.enums.Gender;
import com.server.springStudy.validation.annotation.ExistCategories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record MemberJoinRequest(
        @NotBlank String name,
        Gender gender,
        @Size(min = 5, max = 12) String address,
        @Size(min = 5, max = 12) String specAddress,
        @ExistCategories List<Long> preferCategory
) {
}
