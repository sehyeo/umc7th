package umc.workbook7th.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.workbook7th.domain.Member;
import umc.workbook7th.domain.Mission;
import umc.workbook7th.domain.common.BaseEntity;
import umc.workbook7th.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id") // MemberMission 테이블의 외래 키 컬럼
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission_id") // MemberMission 테이블의 외래 키 컬럼
    private Mission mission;
}