package com.Hackathon.glow.user.controller;

import com.Hackathon.glow.user.dto.UserRequest;
import com.Hackathon.glow.user.dto.UserResponse;
import com.Hackathon.glow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

//유저 생성
    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody UserRequest userRequest)
    {
        Long id=userService.createUser(userRequest);
        return ResponseEntity.ok(id);
    }

    //유저 정보 조회(id로 조회)
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId)
    {
        UserResponse userResponse = userService.getUser(userId);
        return ResponseEntity.ok(userResponse);
    }
}
