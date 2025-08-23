package com.Hackathon.generic.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    boolean isSuccess;

    boolean isFirst;
}
