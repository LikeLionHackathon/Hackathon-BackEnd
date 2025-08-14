package com.Hackathon.glow.UserPreference.domain;

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

    private Long QuestionId;
    private String QuestionContent;

    private Long AnswerId;
    private String AnswerContent;



}
