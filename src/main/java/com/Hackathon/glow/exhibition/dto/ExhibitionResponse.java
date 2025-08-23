package com.Hackathon.glow.exhibition.dto;


import com.Hackathon.glow.tag.domain.Tag;
import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExhibitionResponse {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private String posterImage;
    private List<String> artworkUrl;

    @Builder.Default
    private boolean isOngoing=true;
    private List<String> tags;
    //전시 랑, 작품 리스트 받아오게 ..
    public static ExhibitionResponse from(Exhibition exhibition,List<Artwork> artworks,List<Tag> tags) {
        return ExhibitionResponse.builder()
                .id(exhibition.getId())
                .title(exhibition.getTitle())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .location(exhibition.getLocation())
                .description(exhibition.getDescription())
//                .posterImage(exhibition.getPosterImage())
                .artworkUrl(artworks.stream()
                        .map(Artwork::getArtworkUrl)
                        .filter(Objects::nonNull)
                        .toList()

                )
                .tags(tags.stream().map(tag ->tag.getTagName()).toList())
                .build();
    }

}