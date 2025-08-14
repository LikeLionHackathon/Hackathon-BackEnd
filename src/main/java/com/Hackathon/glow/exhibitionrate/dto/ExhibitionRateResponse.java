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
    private String content;
    private Long userId;      // 작성자 ID
    private Long exhibitionId;
    private LocalDate createdAt;

    public static ExhibitionRateResponse from(ExhibitionRate rate) {
        return ExhibitionRateResponse.builder()
                .id(rate.getId())
                .rate(rate.getRate())
                .content(rate.getContent())
                .userId(rate.getUser() != null ? rate.getUser().getUserId() : null)
                .exhibitionId(rate.getExhibition() != null ? rate.getExhibition().getId() : null)
                .createdAt(rate.getCreatedAt())
                .build();
    }

}
