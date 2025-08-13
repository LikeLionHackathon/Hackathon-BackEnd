package com.Hackathon.glow.ExhibitionLike.controller;

import com.Hackathon.glow.ExhibitionLike.dto.ExhibitionLikeRequest;
import com.Hackathon.glow.ExhibitionLike.dto.ExhibitionLikeResponse;
import com.Hackathon.glow.ExhibitionLike.service.ExhibitionLikeService;
import com.Hackathon.glow.exhibitionrate.service.ExhibitionRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitions/{exhibitionId}/likes")
@RequiredArgsConstructor

public class ExhibitionLikeController {
    private final ExhibitionLikeService exhibitionLikeService;


    //좋아요 생샹
    @PostMapping
    public ResponseEntity<String> createExhibitionLike(@RequestBody ExhibitionLikeRequest request)
    {
        String liked=exhibitionLikeService.createExhibitionLike(request);
        return ResponseEntity.ok(liked);
    }

    //좋아요 ㅈ목록 조회 (유저별)
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExhibitionLikeResponse>> getAllExhibitionLikes(@RequestParam Long userId) {
        List<ExhibitionLikeResponse> response = exhibitionLikeService.getExhibitionLikesByUser(userId);
        return ResponseEntity.ok(response);
    }
    //좋아여 취소

    public ResponseEntity<Void> cancelLike(@RequestParam Long userId,@RequestParam Long exhibitionId)
    {
        exhibitionLikeService.cancelExhibitionLike(userId,exhibitionId);
        return ResponseEntity.noContent().build();

    }
}
