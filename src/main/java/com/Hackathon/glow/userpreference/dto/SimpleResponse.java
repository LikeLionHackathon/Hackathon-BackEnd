package com.Hackathon.glow.userpreference.dto;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SimpleResponse {

    private Long exhibition_id;
    private String title;
    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> tags;

    public SimpleResponse(Exhibition e, List<String> tags) {
        this.exhibition_id = e.getId();
        this.title = e.getTitle();
        this.location = e.getLocation();
        this.startDate = e.getStartDate();
        this.endDate = e.getEndDate();
        this.tags = tags;
    }
}
