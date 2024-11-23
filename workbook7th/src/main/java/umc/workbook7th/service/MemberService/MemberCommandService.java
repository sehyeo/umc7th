package umc.workbook7th.service.MemberService;

import umc.workbook7th.domain.Member;
import umc.workbook7th.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
