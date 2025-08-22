package com.Hackathon.generic.login.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
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
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId)
    {
        UserResponse userresponse = userService.getUserById(userId);
        return ResponseEntity.ok(userresponse);
    }


}
