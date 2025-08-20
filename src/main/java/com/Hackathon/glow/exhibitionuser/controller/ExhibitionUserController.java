package com.Hackathon.glow.exhibitionuser.controller;

import com.Hackathon.glow.exhibitionuser.dto.ExhibitionUserRequest;
import com.Hackathon.glow.exhibitionuser.dto.ExhibitionUserResponse;
import com.Hackathon.glow.exhibitionuser.service.ExhibitionUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibition-users")
@RequiredArgsConstructor
public class ExhibitionUserController {

    private final ExhibitionUserService exhibitionUserService;

    //방문한 전시 표시 (생성 -유저별 )
    @PostMapping
    public ResponseEntity<ExhibitionUserResponse> createVisitedExhibition(@RequestBody ExhibitionUserRequest exhibitionUserRequest) {
        ExhibitionUserResponse response =exhibitionUserService.createVisitedExhibitionUser(exhibitionUserRequest);
        return ResponseEntity.ok(response);
    }

    //방문한 전시 조회 (유저별)
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ExhibitionUserResponse>> getVisitedExhibitionByUser(@PathVariable Long userId) {
        List<ExhibitionUserResponse> responses=exhibitionUserService.getVisitedExhibitionUser(userId);
    return ResponseEntity.ok(responses);
    }

}
