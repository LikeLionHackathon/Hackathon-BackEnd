package com.Hackathon.glow.userpreference.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PreferenceExhibitionResponse {

    Long ExhibitionId;
    String title;
    String reason;
}
