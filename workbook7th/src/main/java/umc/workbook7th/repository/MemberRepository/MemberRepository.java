package umc.workbook7th.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.workbook7th.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
