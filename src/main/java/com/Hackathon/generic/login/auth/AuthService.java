package com.Hackathon.generic.login.auth;


import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String LOGIN_SESSION_KEY ="LOGIN_user";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인 처리
    public void login(String loginId,String password, HttpSession session) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //로그인 성공한 사용자 정보를 서버세션에 저장
        session.setAttribute(LOGIN_SESSION_KEY, user);
        //세션 유효시간 ( 30분 )
        session.setMaxInactiveInterval(1800);
    }

    //세션에서 로그인 사용자 조회
    public User getLoginUser(HttpSession session)
    {
        User user = (User)session.getAttribute(LOGIN_SESSION_KEY);

        if(user == null) {
            throw new IllegalArgumentException("로그인된 사용자가 없습니다.");

        }
        return user;
    }

    //로그아웃 처리
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
