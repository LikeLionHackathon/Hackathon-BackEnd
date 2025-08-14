package com.Hackathon.glow.exhibitionrate.repository;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface ExhibitionRateRepository extends JpaRepository<ExhibitionRate, Long> {

    //특정 전시에 대한 모든 리뷰 조회
    List<ExhibitionRate> findByExhibition(Exhibition exhibition);

    //전시 id로 조회
    List<ExhibitionRate> findByExhibitionId(Long exhibitionId);

    //이용자 id로 조회
//    List<ExhibitionRate> findByUserId(Long userId);
//
//    //전시, 유저 기준으로 조회
//    Optional<ExhibitionRate> findByExhibition_IdAndUser_Id(Long exhibitionId, Long userId);
}



