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
    private LocalDate registeredDate;

    public  Exhibition toEntity(String postImageUrl) {
        return new Exhibition(title, startDate, endDate, teamName, location, description,
            postImageUrl, registeredDate);
    }
}
