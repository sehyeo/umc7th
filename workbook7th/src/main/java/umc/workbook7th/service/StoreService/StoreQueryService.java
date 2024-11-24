package umc.workbook7th.service.StoreService;


import org.springframework.data.domain.Page;
import umc.workbook7th.domain.Mission;
import umc.workbook7th.domain.Review;
import umc.workbook7th.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Mission> getMissionList(Long StoreId, Integer page);
    Page<Review> getReviewList(Long StoreId, Integer page);
}