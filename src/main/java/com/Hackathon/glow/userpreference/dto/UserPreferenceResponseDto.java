package com.Hackathon.glow.userpreference.dto;

import com.Hackathon.glow.userpreference.domain.UserPreference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserPreferenceResponseDto {

    private Long userId;
    private Long userPreferenceId;
    private List<PreferenceAnswerResponseDto> preferenceAnswers;

    //dto를 엔티티로 변환
    @com.fasterxml.jackson.annotation.JsonProperty("preferenceAnswers")
    public static UserPreferenceResponseDto from(UserPreference userPreference) {
        if(userPreference == null) return null;

        return UserPreferenceResponseDto.builder()
                .userId(userPreference.getUser().getUserId())
                .userPreferenceId(userPreference.getUserPreferenceId())
                .preferenceAnswers(
                        (userPreference.getAnswers()==null? Collections.<com.Hackathon.glow.userpreference.domain.PreferenceAnswer>emptyList():userPreference.getAnswers())
                                .stream()
                                .map(PreferenceAnswerResponseDto::from)
                                .collect(Collectors.toList())

                )
                .build();
    }
}
