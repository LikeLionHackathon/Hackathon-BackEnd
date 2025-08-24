package com.Hackathon.glow.exhibitionlike.dto;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibitionlike.domain.ExhibitionLike;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionLikeResponse {

    private Long ExhibitionLikeId;
    private Long userId;
    private Long exhibitionId;
    private boolean liked;
    private String posterImageUrl;

    public static ExhibitionLikeResponse from(ExhibitionLike exhibitionLike) {

        Exhibition exhibition = exhibitionLike.getExhibition();
        return ExhibitionLikeResponse.builder()
                .ExhibitionLikeId(exhibitionLike.getId())
                .userId(exhibitionLike.getUser() != null ? exhibitionLike.getUser().getUserId() : null)
                .exhibitionId(exhibitionLike.getExhibition() != null ? exhibitionLike.getExhibition().getId() : null)
                .liked(exhibitionLike.isLiked())
                .posterImageUrl(exhibition != null ? exhibition.getPosterImageUrl() : null)
                .build();

    }

}
