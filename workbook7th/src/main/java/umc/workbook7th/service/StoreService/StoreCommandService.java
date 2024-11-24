package umc.workbook7th.service.StoreService;

import umc.workbook7th.domain.Mission;
import umc.workbook7th.domain.Review;
import umc.workbook7th.domain.Store;
import umc.workbook7th.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);

    Store createStore(StoreRequestDTO.StoreDTO request);

    Mission createMission(Long storeId, StoreRequestDTO.MissionDTO request);
}
