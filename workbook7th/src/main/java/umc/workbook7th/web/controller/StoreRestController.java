package umc.workbook7th.web.controller;

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
import umc.workbook7th.apiPayload.ApiResponse;
import umc.workbook7th.converter.StoreConverter;
import umc.workbook7th.domain.Mission;
import umc.workbook7th.domain.Review;
import umc.workbook7th.domain.Store;
import umc.workbook7th.service.StoreService.StoreCommandService;
import umc.workbook7th.service.StoreService.StoreQueryService;
import umc.workbook7th.validation.annotation.ExistMember;
import umc.workbook7th.validation.annotation.ExistPage;
import umc.workbook7th.validation.annotation.ExistStore;
import umc.workbook7th.web.dto.MissionResponseDTO;
import umc.workbook7th.web.dto.StoreRequestDTO;
import umc.workbook7th.web.dto.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    // private final MemberCommandService

    private final StoreQueryService storeQueryService; // 작성해야 getReviewList에 오류가 안남..
    @PostMapping("/{storeId}/reviews/add")
    @Operation(summary = "특정 가게에 리뷰 작성 API")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @PostMapping("/add")
    @Operation(summary = "가게 등록 API")
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> createStore(@RequestBody @Valid StoreRequestDTO.StoreDTO request) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResultDTO(store));
    }

    @PostMapping("/{storeId}/missions/add")
    @Operation(summary = "특정 가게에 미션 추가 API")
    public ApiResponse<StoreResponseDTO.CreateMissionResultDTO> createMission(@RequestBody @Valid StoreRequestDTO.MissionDTO request, @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateMissionResultDTO(mission));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Review> reviews = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviews));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId, @ExistPage @RequestParam(name = "page") Integer page){
        Page<Mission> missions = storeQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missions));
    }

}
