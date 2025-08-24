package com.Hackathon.glow.artistlike.controller;

import com.Hackathon.glow.artistlike.dto.ArtistLikeRequest;
import com.Hackathon.glow.artistlike.dto.ArtistLikeResponse;
import com.Hackathon.glow.artistlike.service.ArtistLikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artist-likes")
@RequiredArgsConstructor
public class ArtistLikeController {

    private final ArtistLikeService artistLikeService;

    //아티스트 좋아요 생성
    @PostMapping
    public ResponseEntity<String> createArtistLike(@RequestBody ArtistLikeRequest request, HttpSession httpSession) {
        String message = artistLikeService.createArtistLike(request,httpSession);
        return ResponseEntity.ok(message);
    }

    //아티스트 좋아요 목록 조회 (유저별 )
    @GetMapping("/users")
    public ResponseEntity<List<ArtistLikeResponse>> getArtistLikesByUser(HttpSession session) {
        List<ArtistLikeResponse> responses = artistLikeService.getArtistLikesByUser(session);
        return ResponseEntity.ok(responses);
    }

    //아티스트 좋아요 취소
    @DeleteMapping
    public ResponseEntity<Void> cancelArtistLike(HttpSession session,
                                                 @RequestParam Long toUserId) {
        artistLikeService.cancelArtistLike(session, toUserId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
