package com.Hackathon.glow.exhibitionrate.dto;

import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionRateResponse {
    private Long id;
    private Long rate;
    private Long userId;      // 작성자 ID
    private Long exhibitionId;

    public static ExhibitionRateResponse from(ExhibitionRate rate) {
        return ExhibitionRateResponse.builder()
                .id(rate.getId())
                .rate(rate.getRate())
                .userId(rate.getUser() != null ? rate.getUser().getUserId() : null)
                .exhibitionId(rate.getExhibition() != null ? rate.getExhibition().getId() : null)

                .build();
    }

}
