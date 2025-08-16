package com.Hackathon.glow.tag.domain;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tag")
@NoArgsConstructor
@Getter
public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tagName", nullable=false, unique=true, length=50)
    private String tagName;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ExhibitionTag> exhibitionTag = new ArrayList<>();

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
