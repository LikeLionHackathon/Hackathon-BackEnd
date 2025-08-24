package com.Hackathon.glow.artistexhibition.repository;

import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import java.util.List;

import com.Hackathon.glow.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistExhibitionRepository extends JpaRepository<ArtistExhibition, Long> {

    List<ArtistExhibition> findByExhibition_Id(Long exhibitionId);
    List<ArtistExhibition> findByUser(User user);
    List<ArtistExhibition> findByUser_UserId(Long userId);
    //userId로 등록된 전시 개수
    Long countByUser_UserId(Long userId);
}
