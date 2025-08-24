package com.Hackathon.glow.exhibition.dto;


import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExhibitionSearchResponse {

    private Long exhibitionId;
    private  String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String posterImageUrl;

    public static ExhibitionSearchResponse from(Exhibition exhibition) {
        return ExhibitionSearchResponse.builder()
                .exhibitionId(exhibition.getId())
                .title(exhibition.getTitle())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .posterImageUrl(exhibition.getPosterImageUrl())
                .build();
        }
}
