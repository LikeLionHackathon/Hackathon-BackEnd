package com.Hackathon.glow.exhibitionlike.service;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.exhibitionlike.domain.ExhibitionLike;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeRequest;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeResponse;
import com.Hackathon.glow.exhibitionlike.repository.ExhibitionLikeRepository;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.repository.UserRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import jakarta.servlet.http.HttpSession;
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
    private final AuthService authService;


    //전시 좋아요 생성
    @Transactional
    public String createExhibitionLike(Long exhibitionId, HttpSession session)
    {
        //그냥 좋아요 생성 성공 메시지만 보내면 될듯 .
        //로그인 유저 조회
        User user =authService.getLoginUser(session);


        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                       .orElseThrow(() -> new IllegalArgumentException("전시를 찾을 수 없습니다."));
        exhibitionLikeRepository.findByUserAndExhibition(user, exhibition)
                .ifPresent(like -> {
                    throw new IllegalStateException("이미 가보고싶은 전시에 추가된 전시입니다.");
                });

        ExhibitionLike exhibitionLike = ExhibitionLike.builder()
                .user(user)
                .exhibition(exhibition)
                .liked(true)
                .build();

        exhibitionLikeRepository.save(exhibitionLike);

        return "가보고 싶은 전시에 등록되었습니다.";

    }

    //내가 누른 좋아요 목록 ( 세션 로그인으로 .. )
    @Transactional(readOnly = true)
    public List<ExhibitionLikeResponse> getExhibitionLikesByUser(HttpSession session) {
        User user = authService.getLoginUser(session);

        return exhibitionLikeRepository.findByUser(user).stream()
                .map(ExhibitionLikeResponse::from)
                .collect(Collectors.toList());
    }


    //전시 좋아요 취소  - 좋아요 취소 성공 메시지만 보내면 될듯
    @Transactional
    public void cancelExhibitionLike(HttpSession httpsession, Long exhibitionId) {
        User user = authService.getLoginUser(httpsession);
            Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new IllegalArgumentException("전시를 찾을 수 없습니다."));

        ExhibitionLike like = exhibitionLikeRepository.findByUserAndExhibition(user, exhibition)
                .orElseThrow(() -> new IllegalArgumentException("좋아요가 존재하지 않습니다."));

        exhibitionLikeRepository.delete(like);
    }

}
