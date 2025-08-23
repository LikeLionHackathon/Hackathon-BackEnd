package com.Hackathon.glow.user.dto;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    Long userId;
    String nickname;
    String profileImageUrl;
    String email;
    UserType userType;

    public static UserResponse of(User user) {
        return new
            UserResponse(user.getUserId(), user.getNickname(), user.getProfileImageUrl(),
            user.getEmail(),user.getUserType());
    }
}
