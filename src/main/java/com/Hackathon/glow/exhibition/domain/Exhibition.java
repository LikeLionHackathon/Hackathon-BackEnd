package com.Hackathon.glow.exhibition.domain;

import com.Hackathon.glow.tag.domain.ExhibitionTag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="exhibition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startDate;
    private LocalDate endDate;

    private String teamName;
    private String location;
    private String description;
    private String posterImageUrl;
    //전시 등록 날짜
    private LocalDate registeredDate;

    private Boolean isOngoing = true;

    public Exhibition(String title, LocalDate startDate, LocalDate endDate, String teamName,
        String location, String description, String posterImageUrl, LocalDate registeredDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamName = teamName;
        this.location = location;
        this.description = description;
        this.posterImageUrl = posterImageUrl;
        this.registeredDate = registeredDate;
    }
}