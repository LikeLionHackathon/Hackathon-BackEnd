package com.Hackathon.glow.exhibitionuser.service;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import com.Hackathon.glow.exhibitionuser.domain.ExhibitionUser;
import com.Hackathon.glow.exhibitionuser.dto.ExhibitionUserRequest;
import com.Hackathon.glow.exhibitionuser.dto.ExhibitionUserResponse;
import com.Hackathon.glow.exhibitionuser.repository.ExhibitionUserRepository;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionUserService {

    private final ExhibitionUserRepository exhibitionUserRepository;
    private final ExhibitionRepository exhibitionRepository; //전시정보
    private final UserRepository userRepository;//유저정보

    //방문한 전시 표시
    public ExhibitionUserResponse createVisitedExhibitionUser(ExhibitionUserRequest request) {
//유저 조회
        User user = userRepository.findById(request.getUserid()).orElseThrow(()->new IllegalArgumentException("유저를 찾을 수 없습니다."));

        //전시 조회
        Exhibition exhibition=exhibitionRepository.findById(request.getExhibitionid()).orElseThrow(()->new IllegalArgumentException("전시를 찾을 수 없습니다."));

        //방문 기록 생성
        ExhibitionUser exhibitionuser = ExhibitionUser.builder()
                .user(user)
                .exhibition(exhibition)
                .isVisited(true)
                .visitedDate(LocalDate.now())
                .build();

        //저장
        ExhibitionUser saved=exhibitionUserRepository.save(exhibitionuser);

        return ExhibitionUserResponse.from(saved);


    }

    //방문한 전시 조회 ( 유저별 )
    public List<ExhibitionUserResponse> getVisitedExhibitionUser(Long UserId) {

        List<ExhibitionUser> visitedList=exhibitionUserRepository.findByUser_UserId(UserId);

        return visitedList.stream().map(ExhibitionUserResponse::from).collect(Collectors.toList());

    }


}
