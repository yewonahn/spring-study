package com.server.springStudy.service.storeService;

import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.Store;
import com.server.springStudy.repository.ReviewRepository;
import com.server.springStudy.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Boolean checkStoreExists(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storePage;
    }
}
