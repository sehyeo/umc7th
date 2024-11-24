package umc.workbook7th.service.StoreService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.workbook7th.domain.Mission;
import umc.workbook7th.domain.Review;
import umc.workbook7th.domain.Store;
import umc.workbook7th.repository.MissionRepository.MissionRepository;
import umc.workbook7th.repository.ReviewRepository.ReviewRepository;
import umc.workbook7th.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Mission> getMissionList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId).get();

        Page<Mission> MissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return MissionPage;
    }
}