package com.Hackathon.glow.userpreference.domain;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.userpreference.dto.Preference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPreferenceId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private int questionId;

    @Column(nullable = false)
    private int answerId;

    public UserPreference(User user, int questionId, int answerId) {
        this.user = user;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public String toString() {
        return Preference.answers[questionId][answerId];
    }
}
