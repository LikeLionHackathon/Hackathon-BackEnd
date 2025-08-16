package com.Hackathon.glow.Tag.domain;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="tag")
@NoArgsConstructor
@Getter
public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;


    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
