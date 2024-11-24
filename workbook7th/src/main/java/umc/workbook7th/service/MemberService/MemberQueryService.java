package umc.workbook7th.service.MemberService;

import umc.workbook7th.domain.Member;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
}
