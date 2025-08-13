package com.Hackathon.glow.Tag.domain;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

}
