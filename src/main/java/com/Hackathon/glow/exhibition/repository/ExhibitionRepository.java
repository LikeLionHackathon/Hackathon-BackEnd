package com.Hackathon.glow.exhibition.repository;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

//    //전시 : 제목으로 검색
//    List<Exhibition> findByTitle(String title);
//
//    //id 로
//
//    //전시 : 작가로 검색
//    List<Exhibition> findByTeamName(String teamName);
//
////전시 : 위치로 검색
//    List<Exhibition> findByLocation(String location);
//
//    //전시:tag별로 검색
//    List<Exhibition> findByTagName(String tagName);
}

//전시 등록정보 조회
//전시 삭제
//전시 등록
//전시 정보 수정
//전시 작품 삭제