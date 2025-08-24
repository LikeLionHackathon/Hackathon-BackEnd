package com.Hackathon.glow.exhibition.dto;


import com.Hackathon.glow.tag.domain.Tag;
import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.user.dto.UserResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExhibitionDetailResponse {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private String posterImage;
    private List<String> artworkUrl;
    private List<UserResponse> artists;

    @Builder.Default
    private boolean isOngoing=true;
    private List<String> tags;
    //전시 랑, 작품 리스트 받아오게 ..
    public static ExhibitionDetailResponse from(Exhibition exhibition,List<Artwork> artworks,List<Tag> tags,List<UserResponse> artists) {
        return ExhibitionDetailResponse.builder()
            .id(exhibition.getId())
            .title(exhibition.getTitle())
            .startDate(exhibition.getStartDate())
            .endDate(exhibition.getEndDate())
            .location(exhibition.getLocation())
            .description(exhibition.getDescription())
            .artists(artists)
            .posterImage(exhibition.getPosterImageUrl())
            .artworkUrl(artworks.stream()
                .map(Artwork::getArtworkUrl)
                .filter(Objects::nonNull)
                .toList()

            )
            .tags(tags.stream().map(tag ->tag.getTagName()).toList())
            .build();
    }

    

}