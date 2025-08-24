package com.Hackathon.glow.userpreference.controller;

import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.userpreference.dto.ThemeTagExhibitionDto;
import com.Hackathon.glow.userpreference.dto.UserPreferenceRequest;
import com.Hackathon.glow.userpreference.service.UserPreferenceService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;


    @PostMapping("/preferences")
    public String createPreference(UserPreferenceRequest request, HttpSession session) {
        userPreferenceService.createPreference(request, session);
        return "취향 생성 성공~";
    }

    @GetMapping("exhibitions/tag/theme/recommend")
    public ResponseEntity<ThemeTagExhibitionDto> getByUserThemeTag(HttpSession session) {
        return ResponseEntity.ok(userPreferenceService.getByUserThemeTag(session));
    }

    @GetMapping("exhibitions/tag/mood/recommend")
    public ResponseEntity<ThemeTagExhibitionDto> getByUserMoodTag(HttpSession session) {
        return ResponseEntity.ok(userPreferenceService.getByUserMoodTag(session));
    }
}
