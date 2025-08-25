package com.Hackathon.glow.exhibitionrate.domain;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="exhibitionrate")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //별점

    @NotNull
    @Min(1) @Max(5)
    private double rate;


    @ManyToOne
    @JoinColumn(name="userId")
    private User user;


    @ManyToOne
    @JoinColumn(name="exhibitionId")
    private Exhibition exhibition;



}
