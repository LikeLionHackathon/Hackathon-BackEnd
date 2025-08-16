package com.Hackathon.glow.tag.dto;

import com.Hackathon.glow.tag.domain.ExhibitionTag;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionTagResponse {
    private Long exhibitionId;
    private Long tagId;
    private String tagName;

    public static ExhibitionTagResponse from(ExhibitionTag exhibitionTag) {
        return ExhibitionTagResponse.builder()
                .exhibitionId(exhibitionTag.getExhibition().getId())
                .tagId(exhibitionTag.getTag().getId())
                .tagName(exhibitionTag.getTag().getTagName())
                .build();
    }
}
