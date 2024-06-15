package com.server.springStudy.service.storeService;

import com.server.springStudy.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    @Override
    public Boolean checkStoreExists(Long storeId) {
        return storeRepository.existsById(storeId);
    }
}
