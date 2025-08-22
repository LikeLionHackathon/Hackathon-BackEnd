package com.Hackathon.glow.user.controller;

import com.Hackathon.glow.user.dto.SignUpRequest;
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
    public ResponseEntity<Long> addUser(@RequestBody SignUpRequest request)
    {
        Long id =userService.createUser(request);
        return ResponseEntity.ok(id);
    }



}
