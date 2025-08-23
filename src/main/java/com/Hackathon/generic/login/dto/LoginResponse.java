package com.Hackathon.generic.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    Long userId;

    boolean isSuccess;

    boolean isFirst;
}
