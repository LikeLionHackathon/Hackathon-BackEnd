package com.Hackathon.glow.exhibition.dto;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder

public class ExhibitionCardResponse {

    private Long exhibitionId;
    private String title;
    private String posterImageUrl;

    public static ExhibitionCardResponse from(Exhibition exhibition)
    {
        return ExhibitionCardResponse.builder()
                .exhibitionId(exhibition.getId())
                .title(exhibition.getTitle())
                .posterImageUrl(exhibition.getPosterImageUrl())
                .build();
    }
}
