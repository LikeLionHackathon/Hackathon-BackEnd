package com.Hackathon.glow.Tag.domain;

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

    @ManyToOne
    @JoinColumn(name="exhibitionId")
    private Exhibition exhibition;

    @ManyToOne
    @JoinColumn(name="TagId")
    private Tag tag;



}
