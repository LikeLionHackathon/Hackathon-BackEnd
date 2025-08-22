package com.Hackathon.glow.user.dto;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SignUpRequest {


    private String loginId;

    private String password;

    private String nickname;

    private String email;

    public User toEntity() {
        return User.builder()
            .loginId(loginId)
            .password(password)
            .nickname(nickname)
            .email(email)
            .userType(UserType.VIEWER)
            .build();
    }
}
