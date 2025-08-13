package com.Hackathon.glow.ExhibitionLike.dto;

import com.Hackathon.glow.ExhibitionLike.domain.ExhibitionLike;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionLikeResponse {

    private Long id;
    private Long userId;
    private Long exhibitionId;
    private boolean liked;

    public static ExhibitionLikeResponse from(ExhibitionLike exhibitionLike) {
        return ExhibitionLikeResponse.builder()
                .id(exhibitionLike.getId())
                .userId(exhibitionLike.getUser() != null ? exhibitionLike.getUser().getUserId() : null)
                .exhibitionId(exhibitionLike.getExhibition() != null ? exhibitionLike.getExhibition().getId() : null)
                .liked(exhibitionLike.isLiked())
                .build();

    }

}
