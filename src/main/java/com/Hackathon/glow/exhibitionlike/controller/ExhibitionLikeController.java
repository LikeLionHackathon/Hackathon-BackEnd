package com.Hackathon.glow.exhibitionlike.controller;

import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeRequest;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeResponse;
import com.Hackathon.glow.exhibitionlike.service.ExhibitionLikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitions")
@RequiredArgsConstructor

public class ExhibitionLikeController {
    private final ExhibitionLikeService exhibitionLikeService;


    //좋아요 생성 (가보고싶어요 선택 )
    @PostMapping("/{exhibitionId}/likes")
    public ResponseEntity<String> createExhibitionLike(@PathVariable Long exhibitionId, HttpSession httpSession)
    {
        String liked=exhibitionLikeService.createExhibitionLike(exhibitionId,httpSession);
        return ResponseEntity.ok(liked);
    }

    //좋아요 목록 조회 ( 유저별 )
    @GetMapping("/likes")
    public ResponseEntity<List<ExhibitionLikeResponse>> getAllExhibitionLikes(HttpSession httpSession) {
        List<ExhibitionLikeResponse> response = exhibitionLikeService.getExhibitionLikesByUser(httpSession);
        return ResponseEntity.ok(response);
    }

    //좋아요 취소
    @DeleteMapping("/{exhibitionId}/likes")
    public ResponseEntity<Void> cancelLike(HttpSession httpSession, @RequestParam Long exhibitionId)
    {
        exhibitionLikeService.cancelExhibitionLike(httpSession,exhibitionId);
        return ResponseEntity.noContent().build();
    }
}
