package com.Hackathon.glow.exhibitionlike.domain;


import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="exhibitionLike")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionLike {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    //좋아요
    private boolean liked;

    @ManyToOne
    @JoinColumn(name="exhibitionId")
    private Exhibition exhibition;
}
