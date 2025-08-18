package com.Hackathon.glow.exhibitionspace.dto;


import com.Hackathon.glow.exhibitionspace.domain.ExhibitionSpace;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionSpaceRequestDto {

    @NotNull(message="위치는 필수 입력값입니다.")
    private String location;

    @NotNull(message="대여 가능일 입력은 필수입니다.")
    private LocalDate startDate;

    @NotNull(message="대여 가능일 입력은 필수입니다.")
    private LocalDate endDate;

    @NotNull(message = "비용은 필수 입력값입니다.")
    @Positive(message = "비용은 0보다 커야 합니다.")
    private Long cost;

    @NotNull(message = "평수는 필수입니다.")
    @Positive(message = "평수는 0보다 커야 합니다.")
    private Double size;

    @NotBlank(message = "공간 설명은 필수입니다.")
    private String description;

    private String imageUrl;

    //private Long userId; (로그인 세션에서 받나?)


}
