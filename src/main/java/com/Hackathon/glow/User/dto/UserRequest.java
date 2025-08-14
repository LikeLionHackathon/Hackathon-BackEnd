package com.Hackathon.glow.User.dto;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.User.domain.UserType;
import com.Hackathon.glow.UserPreference.domain.UserPreference;
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
    private String userType;


    public static UserType fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserType 값이 null 또는 비어있습니다.");
        }

        try {
            return UserType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 UserType 값: " + value, e);
        }
    }


    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .profile(profile)
                .userType(fromString(userType))
                .build();
    }

}
