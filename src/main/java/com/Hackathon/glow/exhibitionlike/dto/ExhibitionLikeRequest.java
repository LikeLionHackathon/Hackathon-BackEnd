package com.Hackathon.glow.exhibitionlike.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionLikeRequest {
    //누가
    private Long userId;
    //어떤 전시에
    private Long exhibitionId;
}
