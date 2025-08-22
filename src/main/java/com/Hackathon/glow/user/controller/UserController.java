package com.Hackathon.glow.user.controller;

import com.Hackathon.generic.login.user.UserRequest;
import com.Hackathon.generic.login.user.UserResponse;
import com.Hackathon.glow.user.dto.UserRequest;
import com.Hackathon.glow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    //유저 생성
    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody UserRequest request)
    {
        Long id =userService.createUser(request);
        return ResponseEntity.ok(id);
    }

    //유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<com.Hackathon.generic.login.user.UserResponse> getUser(@PathVariable Long userId)
    {
        UserResponse userresponse = userService.getUserById(userId);
        return ResponseEntity.ok(userresponse);
    }


}
