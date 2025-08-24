package com.Hackathon.glow.userpreference.controller;

import com.Hackathon.glow.userpreference.dto.UserPreferenceRequest;
import com.Hackathon.glow.userpreference.service.UserPreferenceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/preferences")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;


    @PostMapping
    public String createPreference(UserPreferenceRequest request, HttpSession session) {
        userPreferenceService.createPreference(request, session);
        return "취향 생성 성공~";
    }
}
