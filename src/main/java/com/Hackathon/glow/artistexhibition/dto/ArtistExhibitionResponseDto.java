package com.Hackathon.glow.artistexhibition.dto;

import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


    /*
    전시 제목
전시 startDate
전시 endDate
전시 posterImage
     */
public class ArtistExhibitionResponseDto {

    private  Long artistId;
    private  String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String posterImageUrl;

    public static ArtistExhibitionResponseDto from(ArtistExhibition artistExhibition) {
        return ArtistExhibitionResponseDto.builder()
                .artistId(artistExhibition.getUser().getUserId())
                .title(artistExhibition.getExhibition().getTitle())
                .startDate(artistExhibition.getExhibition().getStartDate())
                .endDate(artistExhibition.getExhibition().getEndDate())
                .posterImageUrl(artistExhibition.getExhibition().getPosterImageUrl())
                .build();
    }

}
