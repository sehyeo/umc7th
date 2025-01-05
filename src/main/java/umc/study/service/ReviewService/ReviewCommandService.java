package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    @Transactional
    Review createReview(ReviewRequestDTO.ReviewDto request, Long memberId);
}
