package com.Hackathon.glow.artistlike.dto;


import com.Hackathon.glow.artistlike.domain.ArtistLike;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistLikeResponse {

    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private boolean liked;

    public static ArtistLikeResponse from(ArtistLike artistLike) {
        return ArtistLikeResponse.builder()
                .id(artistLike.getId())
                .fromUserId(artistLike.getFromUser() != null ? artistLike.getFromUser().getUserId() : null)
                .toUserId(artistLike.getToUser() != null ? artistLike.getToUser().getUserId() : null)
                .liked(artistLike.isLiked())
                .build();
    }
}
