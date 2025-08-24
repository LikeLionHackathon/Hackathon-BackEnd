package com.Hackathon.glow.user.service;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.artistexhibition.repository.ArtistExhibitionRepository;
import com.Hackathon.glow.artistlike.repository.ArtistLikeRepository;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.dto.SignUpRequest;
import com.Hackathon.glow.user.dto.UserCardResponse;
import com.Hackathon.glow.user.dto.UserResponse;
import com.Hackathon.glow.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;
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
    private final ArtistExhibitionRepository artistExhibitionRepository;
    private final ArtistLikeRepository artistLikeRepository;
    private final AuthService authService;

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

    //작가 상세페이지 유저카드 불러오기
    public UserCardResponse getUserCard(Long userId) {

        User user =userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("작가를 찾지 못했습니다."));
        Long exhibitionCount = artistExhibitionRepository.countByUser_UserId(user.getUserId());
        Long likeCount = artistLikeRepository.countByToUser_UserIdAndLikedTrue(user.getUserId());

        return UserCardResponse.from(user, exhibitionCount, likeCount);
    }

    //로그인한 유저 유저카드 불러오기
    public UserCardResponse getMyUserCard(HttpSession session) {
        User me =authService.getLoginUser(session);
        long exhibitionCount = artistExhibitionRepository.countByUser_UserId(me.getUserId());
        long likeCount = artistLikeRepository.countByToUser_UserIdAndLikedTrue(me.getUserId());

        return UserCardResponse.from(me,exhibitionCount,likeCount);

    }
}
