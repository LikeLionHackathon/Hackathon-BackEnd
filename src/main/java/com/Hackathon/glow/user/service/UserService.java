package com.Hackathon.glow.user.service;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.dto.SignUpRequest;
import com.Hackathon.glow.user.dto.UserResponse;
import com.Hackathon.glow.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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
    public Long createUser(SignUpRequest request) {

        //request ->entity 변환 (
        User user = request.toEntity();

        //비밀번호 암호화 적용
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //저장
        userRepository.save(user);
        return user.getUserId();

    }

    public UserResponse findByNickname(String nickname) {
        Optional<User> byNickname = userRepository.findByNickname(nickname);
        if (byNickname.isEmpty()) {
            return null;
        }
        return UserResponse.of(byNickname.get());
    }
}
