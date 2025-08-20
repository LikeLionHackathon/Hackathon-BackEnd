package com.Hackathon.glow.exhibitionuser.dto;


import com.Hackathon.glow.exhibitionuser.domain.ExhibitionUser;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//방문한 전시 목록을 보여주기

public class ExhibitionUserResponse {

    //이 유저가 방문한 전시들의 정보 목록을 보내주는 것
    private Long id;

    private Long exhibitionId;
    private String posterImageUrl;//이거 파일로 넘기나?
    private String title;
    private String teamName;
    //전시 방문 날짜
    private LocalDate visitedDate;

    public static ExhibitionUserResponse from(ExhibitionUser exhibitionUser) {
        return ExhibitionUserResponse.builder()
                .exhibitionId(exhibitionUser.getExhibition().getId())
                .posterImageUrl(exhibitionUser.getExhibition().getPosterImageUrl())
                .title(exhibitionUser.getExhibition().getTitle())
                .teamName(exhibitionUser.getExhibition().getTeamName())
                .visitedDate(exhibitionUser.getVisitedDate())
                .build();
    }

}

