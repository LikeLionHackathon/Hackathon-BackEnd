package com.Hackathon.glow.exhibitionspace.repository;

import com.Hackathon.glow.exhibitionspace.domain.ExhibitionSpace;
import com.Hackathon.glow.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface ExhibitionSpaceRepository extends JpaRepository<ExhibitionSpace, Long> {

    //특정 오너가 등록한 전시 공간 목록 조회
    List<ExhibitionSpace> findByOwner(User owner);

    //전시공간 : 위치로 검색
    List<ExhibitionSpace> findByLocation(String location);

    //사용가능 날짜 범위 내 공간 조회
    List<ExhibitionSpace> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);

    //비용 이하 전시공간 검색
    List<ExhibitionSpace> findByCostLessThanEqual(Long cost);


}
