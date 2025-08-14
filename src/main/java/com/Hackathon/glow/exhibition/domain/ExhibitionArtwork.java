package com.Hackathon.glow.exhibition.domain;

import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="exhibitionartwork")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExhibitionArtwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="exhibitionId")
    private Exhibition exhibition;

    @ManyToOne
    @JoinColumn(name="ArtworkId")
    private Artwork artwork;



}
