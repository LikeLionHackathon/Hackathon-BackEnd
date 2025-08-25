package com.Hackathon.glow.userpreference.controller;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.dto.RecommendListDto;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.userpreference.dto.ThemeTagExhibitionDto;
import com.Hackathon.glow.userpreference.dto.UserPreferenceRequest;
import com.Hackathon.glow.userpreference.service.UserPreferenceService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final AuthService authService;
    private final UserPreferenceService userPreferenceService;

    @PostMapping("/preferences")
    public String createPreference(@RequestBody UserPreferenceRequest preferenceAnswers,
        HttpSession session) {
        userPreferenceService.createPreference(preferenceAnswers, session);
        System.out.println(preferenceAnswers.getPreferenceAnswers());
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

    @GetMapping("/preferences")
    public List<String> getUsersTag(HttpSession session) {
        return userPreferenceService.getUsersTag(session);
    }

    @GetMapping("preferences/dailyRecommend")
    public ResponseEntity<RecommendListDto> dailyRecommend(HttpSession session) {
        List<String> usersTag = userPreferenceService.getUsersTag(session);

        RestTemplate restTemplate = new RestTemplateBuilder()
            .build();

        for (String tag : usersTag) {
            System.out.println(tag);
        }
        String pythonUrl = "http://localhost:8000/dailyRecommend";
        ResponseEntity<RecommendListDto> response =
            restTemplate.postForEntity(pythonUrl, usersTag, RecommendListDto.class);
        return response;
    }

}
