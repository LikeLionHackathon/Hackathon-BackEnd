package com.Hackathon.glow.exhibitionlike.controller;

import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeRequest;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeResponse;
import com.Hackathon.glow.exhibitionlike.service.ExhibitionLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitions")
@RequiredArgsConstructor

public class ExhibitionLikeController {
    private final ExhibitionLikeService exhibitionLikeService;


    //좋아요 생성
    @PostMapping("/{exhibitionId}/likes")
    public ResponseEntity<String> createExhibitionLike(@RequestBody ExhibitionLikeRequest request)
    {
        String liked=exhibitionLikeService.createExhibitionLike(request);
        return ResponseEntity.ok(liked);
    }

    //좋아요 목록 조회 (유저별)
    @GetMapping("/likes")
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
