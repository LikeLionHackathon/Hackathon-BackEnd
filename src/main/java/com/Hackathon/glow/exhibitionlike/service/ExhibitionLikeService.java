package com.Hackathon.glow.exhibitionlike.service;

import com.Hackathon.glow.exhibitionlike.domain.ExhibitionLike;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeRequest;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeResponse;
import com.Hackathon.glow.exhibitionlike.repository.ExhibitionLikeRepository;
import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.User.repository.UserRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionLikeService {

    private final ExhibitionLikeRepository exhibitionLikeRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final UserRepository userRepository;


    //전시 좋아요 생성
    @Transactional
    public String createExhibitionLike(ExhibitionLikeRequest request)
    {
        //그냥 좋아요 생성 성공 메시지만 보내면 될듯 .
        User user = userRepository.findById(request.getUserId())
                      .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
                Exhibition exhibition = exhibitionRepository.findById(request.getExhibitionId())
                       .orElseThrow(() -> new IllegalArgumentException("전시를 찾을 수 없습니다."));
        exhibitionLikeRepository.findByUserAndExhibition(user, exhibition)
                .ifPresent(like -> {
                    throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
                });

        ExhibitionLike exhibitionLike = ExhibitionLike.builder()
                .user(user)
                .exhibition(exhibition)
                .liked(true)
                .build();

        exhibitionLikeRepository.save(exhibitionLike);

        return "좋아요 성공!";

    }

    //전시 좋ㅇ아요 목록 조회 (유저 별 .. )
    @Transactional(readOnly = true)
    public List<ExhibitionLikeResponse> getExhibitionLikesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return exhibitionLikeRepository.findByUser(user).stream()
                .map(ExhibitionLikeResponse::from)
                .collect(Collectors.toList());
    }


    //전시 조하요 취소  - 좋아요 취소 성공 메시지만 보내면 될듯
    @Transactional
    public void cancelExhibitionLike(Long userId, Long exhibitionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new IllegalArgumentException("전시를 찾을 수 없습니다."));

        ExhibitionLike like = exhibitionLikeRepository.findByUserAndExhibition(user, exhibition)
                .orElseThrow(() -> new IllegalArgumentException("좋아요가 존재하지 않습니다."));

        exhibitionLikeRepository.delete(like);
    }

}
