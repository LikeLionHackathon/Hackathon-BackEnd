package com.Hackathon.glow.artistlike.service;

import com.Hackathon.glow.artistlike.domain.ArtistLike;
import com.Hackathon.glow.artistlike.dto.ArtistLikeRequest;
import com.Hackathon.glow.artistlike.dto.ArtistLikeResponse;
import com.Hackathon.glow.artistlike.repository.ArtistLikeRepository;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ArtistLikeService {

    private final ArtistLikeRepository artistLikeRepository;
    private final UserRepository userRepository;

    //아티스트 좋아요 생성
    @Transactional
    public String createArtistLike(ArtistLikeRequest request) {
        User fromUser = userRepository.findById(request.getFromUserId())
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누른 유저를 찾을 수 없습니다."));
        User toUser = userRepository.findById(request.getToUserId())
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 받은 유저(아티스트)를 찾을 수 없습니다."));

        // 이미 좋아요 했는지 확인
        artistLikeRepository.findByFromUserAndToUser(fromUser, toUser)
                .ifPresent(like -> {
                    throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
                });

        ArtistLike artistLike = ArtistLike.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .liked(true)
                .build();

        artistLikeRepository.save(artistLike);
        return "아티스트 좋아요 성공!";
    }

    //아티스트 좋아요 조회 ( 유저별 : 어떤 아티스트에게 남겼는가 .. )
    @Transactional(readOnly = true)
    public List<ArtistLikeResponse> getArtistLikesByUser(Long fromUserId) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return artistLikeRepository.findByFromUser(fromUser).stream()
                .map(ArtistLikeResponse::from)
                .collect(Collectors.toList());
    }

    //좋아요 취소
    @Transactional
    public void cancelArtistLike(Long fromUserId, Long toUserId) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new IllegalArgumentException("아티스트를 찾을 수 없습니다."));

        ArtistLike like = artistLikeRepository.findByFromUserAndToUser(fromUser, toUser)
                .orElseThrow(() -> new IllegalArgumentException("좋아요가 존재하지 않습니다."));

        artistLikeRepository.delete(like);
    }
}
