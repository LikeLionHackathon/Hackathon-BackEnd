package com.Hackathon.glow.exhibitionuser.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//방문한 전시 기록
public class ExhibitionUserRequest
{
    //누가 방문
    private Long userid;

    //어떤 전시를 방문했는지

private Long exhibitionid;

}
