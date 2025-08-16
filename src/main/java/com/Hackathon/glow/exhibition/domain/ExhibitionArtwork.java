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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exhibitionId", nullable = false)
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ArtworkId", nullable = false)
    private Artwork artwork;



}
