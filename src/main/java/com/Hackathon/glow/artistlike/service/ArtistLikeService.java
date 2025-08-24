    package com.Hackathon.glow.artistlike.service;

    import com.Hackathon.generic.login.auth.AuthService;
    import com.Hackathon.glow.artistlike.domain.ArtistLike;
    import com.Hackathon.glow.artistlike.dto.ArtistLikeRequest;
    import com.Hackathon.glow.artistlike.dto.ArtistLikeResponse;
    import com.Hackathon.glow.artistlike.repository.ArtistLikeRepository;
    import com.Hackathon.glow.user.domain.User;
    import com.Hackathon.glow.user.repository.UserRepository;
    import jakarta.servlet.http.HttpSession;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor

    public class ArtistLikeService {

        private final ArtistLikeRepository artistLikeRepository;
        private final UserRepository userRepository;
        private final AuthService authService;

        //아티스트 좋아요 생성
        @Transactional
        public String createArtistLike(ArtistLikeRequest request, HttpSession session) {
            //로그인 유저 조회 ( 좋아요 누르는 애 )
            User fromUser = authService.getLoginUser(session);

            //좋아요 받는 애
            User toUser = userRepository.findById(request.getToUserId())
                    .orElseThrow(() -> new IllegalArgumentException("좋아요를 받은 유저(아티스트)를 찾을 수 없습니다."));

            // 이미 좋아요 했는지 확인
            artistLikeRepository.findByFromUserAndToUser(fromUser, toUser)
                    .ifPresent(like -> {
                        throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
                    });

            ArtistLike artistLike = ArtistLike.builder()
                    .fromUser(fromUser)
                    .toUser(toUser)
                    .liked(true)
                    .build();

            artistLikeRepository.save(artistLike);
            return "아티스트 좋아요 성공!";
        }

        //아티스트 좋아요 조회 ( 유저별 : 어떤 아티스트에게 남겼는가 .. )
        @Transactional(readOnly = true)
        public List<ArtistLikeResponse> getArtistLikesByUser(HttpSession session) {

            //로그인 유저
            User fromUser = authService.getLoginUser(session);

            return artistLikeRepository.findByFromUser(fromUser).stream()
                    .map(ArtistLikeResponse::from)
                    .collect(Collectors.toList());
        }

        //좋아요 취소
        @Transactional
        public void cancelArtistLike(HttpSession session, Long toUserId) {
            //로그인 유저
            User fromUser = authService.getLoginUser(session);

            User toUser = userRepository.findById(toUserId)
                    .orElseThrow(() -> new IllegalArgumentException("아티스트를 찾을 수 없습니다."));

            ArtistLike like = artistLikeRepository.findByFromUserAndToUser(fromUser, toUser)
                    .orElseThrow(() -> new IllegalArgumentException("좋아요가 존재하지 않습니다."));

            artistLikeRepository.delete(like);
        }
    }
