package com.Hackathon.glow.artwork.dto;

import com.Hackathon.glow.artwork.domain.Artwork;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArtworkResponse {
    //private Long id;
    private String title;
    private String artworkUrl;

    public static ArtworkResponse from(Artwork artwork){
        return ArtworkResponse.builder()
                //.id(artwork.getId())
//                .title(artwork.getTitle())
                .artworkUrl(artwork.getArtworkUrl())
                .build();
    }

}
