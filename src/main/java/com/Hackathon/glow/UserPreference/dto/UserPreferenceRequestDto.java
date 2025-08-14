package com.Hackathon.glow.UserPreference.dto;

import com.Hackathon.glow.UserPreference.domain.PreferenceAnswer;
import com.Hackathon.glow.UserPreference.domain.UserPreference;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserPreferenceRequestDto {

    private Long userId;
    @com.fasterxml.jackson.annotation.JsonProperty("preferenceAnswers")
    private List<PreferenceAnswerRequestDto> preferenceAnswers;

}
//PreferenceAnswer을 바로 받는게 아니라 answeritem이라는 클래스를 여기에 새로 생성해서
//하는게 더 나은지 ..
