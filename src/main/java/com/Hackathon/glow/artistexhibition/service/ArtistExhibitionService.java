package com.Hackathon.glow.artistexhibition.service;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.artistexhibition.dto.ArtistExhibitionResponseDto;
import com.Hackathon.glow.artistexhibition.repository.ArtistExhibitionRepository;
import com.Hackathon.glow.exhibitionlike.dto.ExhibitionLikeResponse;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistExhibitionService {

    private final ArtistExhibitionRepository artistExhibitionRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    //나의 전시 조회

    public List<ArtistExhibitionResponseDto> getMyExhibitions(HttpSession session) {
        //로그인 유저
        User user=authService.getLoginUser(session);


        return artistExhibitionRepository.findByUser(user).stream()
                .map(ArtistExhibitionResponseDto::from)
                .collect(Collectors.toList());

    }

    //작가의 전시 조회

    public List<ArtistExhibitionResponseDto> getAllExhibitions(Long userId) {

        //유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return artistExhibitionRepository.findByUser_UserId(userId).stream()
                .map(ArtistExhibitionResponseDto::from)
                .collect(Collectors.toList());


    }
}
