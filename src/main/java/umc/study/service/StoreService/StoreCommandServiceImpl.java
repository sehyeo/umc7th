package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.*;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

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