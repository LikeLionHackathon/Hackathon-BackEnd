package com.Hackathon.glow.exhibitionrate.dto;

import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder

public class ExhibitionRateListResponse {

    private Long exhibitionId;
    private List<ExhibitionRateResponse> reviews;
    private int totalCount;

    public static ExhibitionRateListResponse from(Long exhibitionId,List<ExhibitionRate>rates)
    {
        List<ExhibitionRateResponse> items = rates.stream()
                .map(ExhibitionRateResponse::from)
                .toList();

        return ExhibitionRateListResponse.builder()
                .exhibitionId(exhibitionId)
                .reviews(items)
                .totalCount(items.size())
                .build();

    }



}
