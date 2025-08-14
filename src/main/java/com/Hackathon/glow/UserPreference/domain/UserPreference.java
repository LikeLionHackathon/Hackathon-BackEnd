package com.Hackathon.glow.UserPreference.domain;

import com.Hackathon.glow.User.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="userpreference")

public class UserPreference {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userPreferenceId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy="userPreference",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<PreferenceAnswer> answers=new ArrayList<>();

}
