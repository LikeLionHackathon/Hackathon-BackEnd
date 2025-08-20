package com.Hackathon.glow.exhibition.dto;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class AiTagRequeset {
    private Long id;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String teamName;
    private String location;
    private String description;
    private String posterImageUrl;
    private List<String> artworkImages;

    public AiTagRequeset(Exhibition exhibition, String posterImageUrl, List<String> artworkImages) {
        this.id = exhibition.getId();
        this.title = exhibition.getTitle();
        this.startDate = exhibition.getStartDate();
        this.location = exhibition.getLocation();
        this.endDate = exhibition.getEndDate();
        this.teamName = exhibition.getTeamName();
        this.description = exhibition.getDescription();
        this.posterImageUrl = posterImageUrl;
        this.artworkImages = artworkImages;
    }

}
