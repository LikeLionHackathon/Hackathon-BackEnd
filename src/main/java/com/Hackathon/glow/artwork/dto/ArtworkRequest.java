package com.Hackathon.glow.artwork.dto;



import com.Hackathon.glow.artwork.domain.Artwork;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArtworkRequest {

    private String title;
    private String artworkUrl;

    public Artwork toEntity(){
        return Artwork.builder()
                .artworkUrl(artworkUrl)
                .build();
    }
}
