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
    private Long exhibitionrateId;

    private Long userId;      // 작성자 ID
    private Long exhibitionId;

    private Long rate;
    public static ExhibitionRateResponse from(ExhibitionRate rate) {
        return ExhibitionRateResponse.builder()
                .exhibitionrateId(rate.getId())
                .rate(rate.getRate())
                .userId(rate.getUser() != null ? rate.getUser().getUserId() : null)
                .exhibitionId(rate.getExhibition() != null ? rate.getExhibition().getId() : null)

                .build();
    }

}
