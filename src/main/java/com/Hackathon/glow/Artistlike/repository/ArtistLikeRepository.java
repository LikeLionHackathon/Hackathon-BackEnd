package com.Hackathon.glow.Artistlike.repository;

import com.Hackathon.glow.Artistlike.domain.ArtistLike;
import com.Hackathon.glow.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ArtistLikeRepository extends JpaRepository<ArtistLike, Long> {

    // 특정 유저가 특정 아티스트(=User) 좋아요 했는지 확인
    Optional<ArtistLike> findByFromUserAndToUser(User fromUser, User toUser);

    // 특정 유저가 좋아요한 아티스트 목록
    List<ArtistLike> findByFromUser(User fromUser);

    // 특정 아티스트를 좋아요한 유저 목록 (필요 시)
    List<ArtistLike> findByToUser(User toUser);

    // 특정 유저가 특정 아티스트 좋아요 취소
    void deleteByFromUserAndToUser(User fromUser, User toUser);
}
