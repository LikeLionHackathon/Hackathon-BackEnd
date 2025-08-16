package com.Hackathon.glow.exhibition.dto;


import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExhibitionRequest {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String teamName;
    private String location;
    private String description;
    private String posterImage;
    private LocalDate registeredDate;
    private boolean isOngoing=true;


    public Exhibition toEntity(){
        return Exhibition.builder()
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .teamName(teamName)
                .location(location)
                .description(description)
                .posterImage(posterImage)
                .registeredDate(registeredDate)
                .build();

    }
}
