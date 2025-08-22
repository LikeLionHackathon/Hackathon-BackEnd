package com.Hackathon.glow.exhibition.dto;

import java.util.List;
import lombok.Data;

@Data
public class RecommendDto {

    Long id;
    String title;
    String description;
    String location;
    String startDate;
    String endDate;
    String posterImageUrl;
    List<String> artworkImages;
    List<String> tags;
    String recommendationReason;
}
