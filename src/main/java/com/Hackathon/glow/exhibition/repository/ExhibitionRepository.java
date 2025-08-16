package com.Hackathon.glow.exhibition.repository;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
//태그 id로 전시 조회
List<Exhibition> findDistinctByExhibitionTags_Tag_Id(Long tagId);

//태그 이름으로 전시 조회
    List<Exhibition> findDistinctByExhibitionTags_Tag_TagName(String tagName);

}

//전시 등록정보 조회
//전시 삭제
//전시 등록
//전시 정보 수정
//전시 작품 삭제