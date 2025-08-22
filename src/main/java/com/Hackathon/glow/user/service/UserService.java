package com.Hackathon.glow.user.service;


import com.Hackathon.generic.login.user.UserRepository;
import com.Hackathon.generic.login.user.UserRequest;
import com.Hackathon.generic.login.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //유저 생성
//유저 id를 받아서 .. db에서 찾고 생성
    public Long createUser(UserRequest userRequest) {

        //request ->entity 변환 (
        User user =userRequest.toEntity();

        //비밀번호 암호화 적용
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        //저장
        userRepository.save(user);
        return user.getId();

    }

    //유저 조회
    public UserResponse getUserById(Long userId)
    {
        //유저 조회
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저가 조회되지 않습니다."));

        return UserResponse.from(user);
    }
}
