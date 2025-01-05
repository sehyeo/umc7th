package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewResponseDTO.ReviewResultDto toReviewResultDTO (Review review){
        return ReviewResponseDTO.ReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReviewDTO (ReviewRequestDTO.ReviewDto request, Member member, Store store){
        return Review.builder()
                .member(member)
                .store(store)
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewDto toReviewPreViewDTO (Review review){
        return ReviewResponseDTO.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDto toReviewPreViewListDTO (Page<Review> reviewList){

        List<ReviewResponseDTO.ReviewPreViewDto> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
