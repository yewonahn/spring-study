package com.server.springStudy.repository;

import com.server.springStudy.domain.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long>  {
}
