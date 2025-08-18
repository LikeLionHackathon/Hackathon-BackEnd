package com.Hackathon.glow.userpreference.domain;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="perference_answer")
public class PreferenceAnswer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userPreferenceId")
    private UserPreference userPreference;

    private Long questionId;
    private String QuestionContent;

    private Long answerId;
    private String AnswerContent;



}
