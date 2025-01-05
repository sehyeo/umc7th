package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements  ReviewCommandService {
    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.ReviewDto request, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Review newReview = ReviewConverter.toReviewDTO(request, member, store);

        return reviewRepository.save(newReview);
    }
}
