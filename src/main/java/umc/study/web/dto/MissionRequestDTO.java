package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistMission;

public class MissionRequestDTO {
    @Getter
    public static class ChallengeMissionDto{
        @ExistMission
        private Long missionId;
    }
}