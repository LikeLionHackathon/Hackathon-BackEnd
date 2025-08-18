package com.Hackathon.glow.user.dto;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private String profile;
    private UserType userType;

    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .profile(profile)
                .userType(userType)
                .build();
    }

}
