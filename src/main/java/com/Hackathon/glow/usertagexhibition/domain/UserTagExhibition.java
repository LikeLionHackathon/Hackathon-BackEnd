package com.Hackathon.glow.usertagexhibition.domain;


import com.Hackathon.glow.userpreference.domain.UserPreference;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usertag_exhibition")

public class UserTagExhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userpreferenceId")
    private UserPreference userPreference;

    @ManyToOne
    @JoinColumn(name="exhibitionId")
    private Exhibition exhibition;


}
