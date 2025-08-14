package com.Hackathon.glow.User.service;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.User.dto.UserRequest;
import com.Hackathon.glow.User.dto.UserResponse;
import com.Hackathon.glow.User.repository.UserRepository;
import com.Hackathon.glow.UserPreference.domain.UserPreference;
import com.Hackathon.glow.UserPreference.repository.UserPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserPreferenceRepository userPreferenceRepository;

    //유저 생성
    public Long createUser(UserRequest userRequest) {

        //유저 저장
        User saved =userRepository.save(userRequest.toEntity());
        return saved.getUserId();

    }

    //유저 정보 조회
    @Transactional(readOnly = true)
    public UserResponse getUser(Long userId) {
        //유저 조회
        User user=userRepository.findByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("유저가 조회되지 않습니다 :"+ userId));

        //유저 취향 정보 조회
        UserPreference userPreference =userPreferenceRepository.findByUser(user)
                .orElse(null);

        return UserResponse.from(user,userPreference);

    }

}
