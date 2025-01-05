package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistPage;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.ReviewResultDto> createReview(@RequestBody @Valid ReviewRequestDTO.ReviewDto request,
                                                                       @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = reviewCommandService.createReview(request, memberId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }

    @GetMapping("/stores/{storeId}")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDto> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @ExistPage @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<Review> reviewList = reviewQueryService.getReviewList(storeId, page - 1);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }

    @GetMapping("/myReviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API",description = "내가 작성한  리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 id"),
            @Parameter(name = "page", description = "페이지 번호")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDto> getMyReviewList(@ExistMember @RequestParam(name = "memberId") Long memberId,
                                                                               @ExistPage @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<Review> myReviewList = reviewQueryService.getMyReviewList(memberId, page - 1);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(myReviewList));
    }
}
