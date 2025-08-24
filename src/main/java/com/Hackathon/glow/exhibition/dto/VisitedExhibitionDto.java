package com.Hackathon.glow.exhibition.dto;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.user.dto.UserResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class VisitedExhibitionDto {

    Long exhibitionId;

    String title;

    int rate;

    LocalDate startDate;

    String posterImageUrl;

    List<UserResponse> artists;

    public VisitedExhibitionDto(Exhibition e, List<UserResponse> artists) {
        this.exhibitionId = e.getId();
        this.title = e.getTitle();
        this.posterImageUrl = e.getPosterImageUrl();
        this.artists = artists;
        this.startDate = e.getStartDate();
    }
}
