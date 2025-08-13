package com.Hackathon.glow.UserPreference.service;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.User.repository.UserRepository;
import com.Hackathon.glow.UserPreference.domain.PreferenceAnswer;
import com.Hackathon.glow.UserPreference.domain.UserPreference;
import com.Hackathon.glow.UserPreference.dto.PreferenceAnswerResponseDto;
import com.Hackathon.glow.UserPreference.dto.UserPreferenceRequestDto;
import com.Hackathon.glow.UserPreference.dto.UserPreferenceResponseDto;
import com.Hackathon.glow.UserPreference.repository.PreferenceAnswerRepository;
import com.Hackathon.glow.UserPreference.repository.UserPreferenceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPreferenceService {
    private final UserPreferenceRepository userPreferenceRepository;
    private final PreferenceAnswerRepository preferenceAnswerRepository;
    private final UserRepository userRepository;

    //유저 취향 생성
    public UserPreferenceResponseDto createUserPreference(UserPreferenceRequestDto userPreferenceRequestDto) {

        //유저 먼저 찾기
        User user = userRepository.findByUserId(userPreferenceRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없음"));
        //유저 preference 엔티티 생성

        UserPreference userPreference = UserPreference.builder()
                .user(user)
                .build();

        //유저 preference 저장 -> 그래야 answer들 받기 가능
        userPreferenceRepository.save(userPreference);

        //preference answer 리스트 생성
        if(userPreferenceRequestDto.getAnswers() != null) {
            for(PreferenceAnswer answer: userPreferenceRequestDto.getAnswers()) {
                answer.setUserPreference(userPreference);//관계 설정 ( 어느 유저의 취향인지 )
                preferenceAnswerRepository.save(answer);
            }
        }
        return UserPreferenceResponseDto.from(userPreference);
    }

    //유저 취향 조회
@Transactional(readOnly=true)
    public List<PreferenceAnswerResponseDto> getUserPreference(Long userId) {

        //유저의 preferenceid 로 유저의 취향 조회
    UserPreference userPreference=userPreferenceRepository.findByUser_UserId(userId)
            .orElseThrow(()->new IllegalArgumentException("해당 취향 정보를 찾을 수 없음"));
        //해당 userpreference에 연결된 preference답변 리스트 갖고오기
    List<PreferenceAnswer> answers=preferenceAnswerRepository.findByUserPreference(userPreference);

    return answers.stream().map(PreferenceAnswerResponseDto::from).toList();
    }

}
