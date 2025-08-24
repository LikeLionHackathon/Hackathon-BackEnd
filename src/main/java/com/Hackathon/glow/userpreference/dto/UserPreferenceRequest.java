package com.Hackathon.glow.userpreference.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserPreferenceRequest {
    int userType;
    List<PrefrenceAnswerRequest> preferenceAnswers;
}
