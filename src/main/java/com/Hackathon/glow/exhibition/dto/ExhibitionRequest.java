package com.Hackathon.glow.exhibition.dto;


import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @Future(message = "전시 시작일은 미래 날짜여야 합니다.")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @Future(message = "전시 종료일은 미래 날짜여야 합니다.")
    private LocalDate endDate;

    @AssertTrue(message = "종료일은 시작일 이후여야 합니다.")
    public boolean isEndAfterStart() {
        if (startDate == null || endDate == null) return true; // null 체크
        return endDate.isAfter(startDate);
    }

    private List<Long> artists;
    private String location;
    private String description;
    private LocalDate registeredDate;

    public  Exhibition toEntity(String postImageUrl) {
        return new Exhibition(title, startDate, endDate, location, description,
            postImageUrl, registeredDate);
    }
}
