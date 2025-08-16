package com.Hackathon.glow.exhibition.domain;

import com.Hackathon.glow.Tag.domain.ExhibitionTag;
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
    private String posterImage;

    //전시 등록 날짜
    private LocalDate registeredDate;

    //전시중/ 전시끝 표시
    private boolean isOngoing=true;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExhibitionTag> exhibitionTags = new ArrayList<>();

}
