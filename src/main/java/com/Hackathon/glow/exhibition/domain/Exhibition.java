package com.Hackathon.glow.exhibition.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private String posterImage;

    //전시 등록 날짜
    private LocalDate registeredDate;



}
