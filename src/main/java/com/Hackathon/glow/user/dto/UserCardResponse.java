package com.Hackathon.glow.user.dto;


import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserCardResponse {

    private Long userId;
    private String username;
    private UserType userType;
    private String email;
    private Long exhibitionCount;
    private Long likeCount;

    public static UserCardResponse from(User user,Long exhibitionCount,Long likeCount) {
        return UserCardResponse.builder()
                .userId(user.getUserId())
                .username(user.getNickname())
                .userType(user.getUserType())
                .email(user.getEmail())
                //이거는 내 artistId로 등록된 전시를 받는거거든 ?
                //artistId에
                .exhibitionCount(exhibitionCount)
                //ArtistLike에서 내가 받은 좋아요 개수를 불러와야 해.
                .likeCount(likeCount)
                .build();




    }

}
