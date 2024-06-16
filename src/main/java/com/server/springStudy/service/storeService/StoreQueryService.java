package com.server.springStudy.service.storeService;

import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.Store;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StoreQueryService {
    Boolean checkStoreExists(Long storeId);

    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long StoreId, Integer page);
}
