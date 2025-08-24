package com.Hackathon.glow.user.controller;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.dto.SignUpRequest;
import com.Hackathon.glow.user.dto.UserCardResponse;
import com.Hackathon.glow.user.dto.UserResponse;
import com.Hackathon.glow.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;


    //유저 생성
    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody SignUpRequest request) {
        Long id = userService.createUser(request);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<UserResponse> readUserByNickname(String nickname) {
        return ResponseEntity.ok(userService.findByNickname(nickname));
    }

    //유저 카드 정보 불러오기 ( 로그인한 유저 )
    @GetMapping("/usercard")
    public ResponseEntity<UserCardResponse> readUserCardByUserId(HttpSession session) {

        return ResponseEntity.ok(userService.getMyUserCard(session));

    }


    //유저 카드 정보 불러오기 ( 작가 상세페이지 - id로 )
    @GetMapping("/usercard/{userId}")
    public ResponseEntity<UserCardResponse> readUserCardByUserId(@PathVariable Long userId) {

        return ResponseEntity.ok(userService.getUserCard(userId));

    }

}
