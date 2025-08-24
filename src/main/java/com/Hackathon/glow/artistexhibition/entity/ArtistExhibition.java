package com.Hackathon.glow.artistexhibition.entity;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ArtistExhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @ManyToOne
    @JoinColumn(name = "exhibition_id")
    Exhibition exhibition;

    public ArtistExhibition(User user, Exhibition exhibition) {
        this.user = user;
        this.exhibition = exhibition;
    }
}
