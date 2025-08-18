package com.Hackathon.glow.artistlike.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistLikeRequest {

    //누가
    private Long fromUserId;

    //누구에게
    private Long toUserId;
}
