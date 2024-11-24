package umc.workbook7th.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.workbook7th.converter.StoreConverter;
import umc.workbook7th.domain.Mission;
import umc.workbook7th.domain.Review;
import umc.workbook7th.domain.Store;
import umc.workbook7th.repository.MemberRepository.MemberRepository;
import umc.workbook7th.repository.MissionRepository.MissionRepository;
import umc.workbook7th.repository.RegionRepository.RegionRepository;
import umc.workbook7th.repository.ReviewRepository.ReviewRepository;
import umc.workbook7th.repository.StoreRepository.StoreRepository;
import umc.workbook7th.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final RegionRepository regionRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }

    @Override
    public Store createStore(StoreRequestDTO.StoreDTO request) {
        Store store = StoreConverter.toStore(request);

        store.setRegion(regionRepository.findById(request.getRegion_id()).get());

        return storeRepository.save(store);
    }

    @Override
    public Mission createMission(Long storeId, StoreRequestDTO.MissionDTO request) {
        Mission mission = StoreConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);
    }
}