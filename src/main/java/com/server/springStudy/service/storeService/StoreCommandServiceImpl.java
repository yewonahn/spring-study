package com.server.springStudy.service.storeService;

import com.server.springStudy.apiPayload.exception.handler.MemberHandler;
import com.server.springStudy.apiPayload.exception.handler.StoreHandler;
import com.server.springStudy.converter.ReviewConverter;
import com.server.springStudy.converter.ReviewImageConverter;
import com.server.springStudy.domain.entity.Member;
import com.server.springStudy.domain.entity.Review;
import com.server.springStudy.domain.entity.ReviewImage;
import com.server.springStudy.domain.entity.Store;
import com.server.springStudy.repository.MemberRepository;
import com.server.springStudy.repository.ReviewImageRepository;
import com.server.springStudy.repository.ReviewRepository;
import com.server.springStudy.repository.StoreRepository;
import com.server.springStudy.web.dto.store.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.server.springStudy.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;
import static com.server.springStudy.apiPayload.code.status.ErrorStatus.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, ReviewCreateRequest request) {

        Review newReview = ReviewConverter.toCreateReview(request);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(STORE_NOT_FOUND));

        newReview.setMember(member);
        newReview.setStore(store);

        return reviewRepository.save(newReview);
    }

    @Override
    public List<ReviewImage> createReviewImage(Review review, ReviewCreateRequest request) {

        List<ReviewImage> reviewImageList = ReviewImageConverter.toReviewImageList(request.imageUrl());

        reviewImageList.forEach(reviewImage -> {
            reviewImage.setReview(review);
            reviewImageRepository.save(reviewImage);
        });

        return reviewImageList;
    }
}