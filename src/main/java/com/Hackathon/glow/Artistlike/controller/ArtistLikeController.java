package com.Hackathon.glow.Artistlike.controller;

import com.Hackathon.glow.Artistlike.dto.ArtistLikeRequest;
import com.Hackathon.glow.Artistlike.dto.ArtistLikeResponse;
import com.Hackathon.glow.Artistlike.service.ArtistLikeService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> createArtistLike(@RequestBody ArtistLikeRequest request) {
        String message = artistLikeService.createArtistLike(request);
        return ResponseEntity.ok(message);
    }

    //아티스트 좋아요 목록 조회 (유저별 )
    @GetMapping("/users/{fromUserId}")
    public ResponseEntity<List<ArtistLikeResponse>> getArtistLikesByUser(@PathVariable Long fromUserId) {
        List<ArtistLikeResponse> responses = artistLikeService.getArtistLikesByUser(fromUserId);
        return ResponseEntity.ok(responses);
    }

    //아티스트 좋아요 취소
    @DeleteMapping
    public ResponseEntity<Void> cancelArtistLike(@RequestParam Long fromUserId,
                                                 @RequestParam Long toUserId) {
        artistLikeService.cancelArtistLike(fromUserId, toUserId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
