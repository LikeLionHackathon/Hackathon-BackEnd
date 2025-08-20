package com.Hackathon.glow.exhibitionuser.domain;


import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name="exhibitionuser")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionUser {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    private Long id;

    //방문 여부
    private boolean isVisited;

    //전시 방문 날짜
    private LocalDate visitedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exhibitionId",nullable = false)
    private Exhibition exhibition;

}
