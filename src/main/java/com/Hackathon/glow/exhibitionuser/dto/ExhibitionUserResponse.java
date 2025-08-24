package com.Hackathon.glow.exhibitionuser.dto;


import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;
import com.Hackathon.glow.exhibitionuser.domain.ExhibitionUser;
import com.Hackathon.glow.user.dto.UserResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    //전시 참여 작가들 <리스트>
    private List<UserResponse> artists;

    //전시 방문 날짜
    private LocalDate visitedDate;

    //내가 매긴 별점
    private Long rate;

    public static ExhibitionUserResponse from(ExhibitionUser exhibitionUser) {
        return ExhibitionUserResponse.builder()
                .exhibitionId(exhibitionUser.getExhibition().getId())
                .posterImageUrl(exhibitionUser.getExhibition().getPosterImageUrl())
                .title(exhibitionUser.getExhibition().getTitle())
              //  .artists(artists.stream().map(ae->UserResponse.of(ae.getUser())).collect(Collectors.toList()))
                .visitedDate(exhibitionUser.getVisitedDate())
              //  .rate(exhibitionRate.getRate())
                .build();
    }

}

