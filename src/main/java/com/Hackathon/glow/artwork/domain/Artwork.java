package com.Hackathon.glow.artwork.domain;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="artwork")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Artwork {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artworkUrl;

}
