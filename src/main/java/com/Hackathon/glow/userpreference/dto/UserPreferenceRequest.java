package com.Hackathon.glow.userpreference.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserPreferenceRequest {

    List<PrefrenceAnswerRequest> preferenceAnswers;
}
