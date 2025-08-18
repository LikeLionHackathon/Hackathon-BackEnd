package com.Hackathon.glow.userpreference.service;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.repository.UserRepository;
import com.Hackathon.glow.userpreference.domain.PreferenceAnswer;
import com.Hackathon.glow.userpreference.domain.UserPreference;
import com.Hackathon.glow.userpreference.dto.PreferenceAnswerRequestDto;
import com.Hackathon.glow.userpreference.dto.PreferenceAnswerResponseDto;
import com.Hackathon.glow.userpreference.dto.UserPreferenceRequestDto;
import com.Hackathon.glow.userpreference.dto.UserPreferenceResponseDto;
import com.Hackathon.glow.userpreference.repository.PreferenceAnswerRepository;
import com.Hackathon.glow.userpreference.repository.UserPreferenceRepository;

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
    @Transactional
    public UserPreferenceResponseDto createUserPreference(UserPreferenceRequestDto reqDto) {
        // 1) 유저 조회
        User user = userRepository.findByUserId(reqDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없음"));

        // 2) 루트 엔티티 생성
        UserPreference up = UserPreference.builder()
                .user(user)
                .build();

        // 3) 자식 엔티티 생성 + 연관관계 연결
        if (reqDto.getPreferenceAnswers() != null) { // ← 여기! getAnswers() 아님
            for (PreferenceAnswerRequestDto a : reqDto.getPreferenceAnswers()) {
                PreferenceAnswer pa = PreferenceAnswer.builder()
                        .questionId(a.getQuestionId())
                        .answerId(a.getAnswerId())
                        .build();
                up.addAnswer(pa); // 양방향 세팅 (cascade로 같이 저장)
            }
        }

        // 4) 저장
        UserPreference saved = userPreferenceRepository.save(up);

        // 5) 응답 DTO 변환
        return UserPreferenceResponseDto.from(saved);
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
