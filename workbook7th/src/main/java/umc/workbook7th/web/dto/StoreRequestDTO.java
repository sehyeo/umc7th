package umc.workbook7th.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }

    @Getter
    public static class StoreDTO {
        @NotNull
        Long id;
        @NotBlank
        String name;
        @NotNull
        String address;
        @NotNull
        Long region_id;
    }

    @Getter
    public static class MissionDTO {
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotNull
        String mission_spec;
    }
}