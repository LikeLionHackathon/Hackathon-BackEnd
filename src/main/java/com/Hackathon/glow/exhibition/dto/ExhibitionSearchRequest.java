package com.Hackathon.glow.exhibition.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionSearchRequest {

    private String title;
    private String artist;

}
