package com.Hackathon.glow.UserPreference.controller;


import com.Hackathon.glow.UserPreference.dto.PreferenceAnswerResponseDto;
import com.Hackathon.glow.UserPreference.dto.UserPreferenceRequestDto;
import com.Hackathon.glow.UserPreference.dto.UserPreferenceResponseDto;
import com.Hackathon.glow.UserPreference.service.UserPreferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/preferences")
@RequiredArgsConstructor

public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    //유저 취향 생성
    @PostMapping
    public ResponseEntity<UserPreferenceResponseDto> createPreference(@Valid @RequestBody UserPreferenceRequestDto requestDto) {

        return ResponseEntity.ok(userPreferenceService.createUserPreference(requestDto));

    }

    //유저 취향 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<PreferenceAnswerResponseDto>> getPreferences( @PathVariable Long userId) {
        List<PreferenceAnswerResponseDto> body = userPreferenceService.getUserPreference(userId);
        return ResponseEntity.ok(body);
    }

}
