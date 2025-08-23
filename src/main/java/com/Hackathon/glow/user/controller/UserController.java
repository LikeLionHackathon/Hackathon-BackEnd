package com.Hackathon.glow.user.controller;

import com.Hackathon.glow.user.dto.SignUpRequest;
import com.Hackathon.glow.user.dto.UserResponse;
import com.Hackathon.glow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    //유저 생성
    @PostMapping("/api/v1/users")
    public ResponseEntity<Long> addUser(@RequestBody SignUpRequest request) {
        Long id = userService.createUser(request);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/api/v1/users/{nickname}")
    public ResponseEntity<UserResponse> readUserByNickname(String nickname) {
        return ResponseEntity.ok(userService.findByNickname(nickname));
    }
}
