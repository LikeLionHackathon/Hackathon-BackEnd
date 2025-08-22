package com.Hackathon.glow.user.dto;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequest {

    private Long userId;

    private String loginId;

    private String username;


    private String password;

    private String profileUrl;

    private UserType userType;
}
