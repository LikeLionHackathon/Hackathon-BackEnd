package com.Hackathon.generic.login.dto;

import com.Hackathon.glow.user.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    Long userId;

    UserType userType;

    boolean isSuccess;

    boolean isFirst;
}
