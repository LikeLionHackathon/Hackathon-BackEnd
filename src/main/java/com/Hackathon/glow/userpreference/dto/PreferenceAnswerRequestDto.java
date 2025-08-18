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
public class PreferenceAnswerRequestDto {

    private Long questionId;
    private Long answerId;

    public PreferenceAnswerRequestDto from(PreferenceAnswer preferenceAnswer) {
        if(preferenceAnswer==null) return null;

        return new PreferenceAnswerRequestDto(preferenceAnswer.getQuestionId(), preferenceAnswer.getAnswerId());


    }
}
