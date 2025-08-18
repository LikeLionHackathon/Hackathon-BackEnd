package com.Hackathon.glow.exhibitionspace.domain;

import com.Hackathon.glow.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "exhibition_space")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionSpace {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //공실위치
    private String location;

    //공실 평수
    private Double size;

    //공간 설명
    private String description;

    //공실 사용 가능날짜
    private LocalDate startDate;
    private LocalDate endDate;

    //공실 대여 비용
    //일별로 고정?
    private Long cost;

    //공간 사진

    private String imageUrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId",unique=true)
    //공실 주인 - N:1 매핑 ( 하나의 주인이 공실 여러개 )
    private User owner;

}
