package com.Hackathon.glow.userpreference.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PrefrenceAnswerRequest {
    int questionId;
    List<Integer> answerId;
}
