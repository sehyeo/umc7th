package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface ReviewQueryService {
    @Transactional
    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Review> getMyReviewList(Long memberId, Integer page);
}
