package com.Hackathon.glow.userpreference.domain;

import com.Hackathon.glow.user.domain.User;
import jakarta.persistence.*;

@Entity
@Table(
    name = "userpreference",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "questionId"})
    }
)
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPreferenceId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private Long answerId;
}
