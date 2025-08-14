package com.Hackathon.glow.ExhibitionLike.repository;

import com.Hackathon.glow.ExhibitionLike.domain.ExhibitionLike;
import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExhibitionLikeRepository extends JpaRepository<ExhibitionLike, Long> {
    // 특정 유저와 전시 조합으로 좋아요 여부 확인
//    Optional<ExhibitionLike> findByUserAndExhibition(User user, Exhibition exhibition);
//
//    // 특정 전시 좋아요 리스트 조회
//    List<ExhibitionLike> findByExhibition(Exhibition exhibition);
//
//    // 특정 유저의 좋아요 목록 조회
//    List<ExhibitionLike> findByUser(User user);
//
//    // 좋아요 삭제 (유저 , 전시 )
//    void deleteByUserAndExhibition(User user, Exhibition exhibition);
}
