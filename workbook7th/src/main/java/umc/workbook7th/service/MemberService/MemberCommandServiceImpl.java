package umc.workbook7th.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.workbook7th.apiPayload.code.status.ErrorStatus;
import umc.workbook7th.apiPayload.exception.handler.FoodCategoryHandler;
import umc.workbook7th.converter.MemberConverter;
import umc.workbook7th.converter.MemberPreferConverter;
import umc.workbook7th.domain.FoodCategory;
import umc.workbook7th.domain.Member;
import umc.workbook7th.domain.mapping.MemberPrefer;
import umc.workbook7th.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.workbook7th.repository.MemberRepository.MemberRepository;
import umc.workbook7th.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
