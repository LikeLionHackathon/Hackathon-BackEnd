package com.Hackathon.glow.userpreference.dto;

import com.Hackathon.glow.userpreference.domain.PreferenceAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceAnswerResponseDto {

    private Long QuestionId;
    private Long AnswerId;

    //변환 ( entity-dto )
    public static PreferenceAnswerResponseDto from(PreferenceAnswer preferenceAnswer) {
        if (preferenceAnswer==null) return null;

        return new PreferenceAnswerResponseDto(preferenceAnswer.getQuestionId(),preferenceAnswer.getAnswerId());
    }
}
