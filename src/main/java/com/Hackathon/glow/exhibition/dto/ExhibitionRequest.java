package com.Hackathon.glow.exhibition.dto;


import com.Hackathon.glow.exhibition.domain.Exhibition;
import java.util.List;
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
    private List<Long> artists;
    private String location;
    private String description;
    private LocalDate registeredDate;

    public  Exhibition toEntity(String postImageUrl) {
        return new Exhibition(title, startDate, endDate, location, description,
            postImageUrl, registeredDate);
    }
}
