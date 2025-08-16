package com.Hackathon.glow.tag.domain;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="exhibitiontag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExhibitionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="exhibitionId")
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="TagId")
    private Tag tag;

    public ExhibitionTag(Exhibition exhibition, Tag tag) {
        this.exhibition = exhibition;
        this.tag = tag;
    }
}