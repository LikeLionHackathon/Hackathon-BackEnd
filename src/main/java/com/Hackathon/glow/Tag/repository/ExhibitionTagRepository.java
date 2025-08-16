package com.Hackathon.glow.Tag.repository;

import com.Hackathon.glow.Tag.domain.ExhibitionTag;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag, Long> {

    //전시 id로 태그 검색
    //여러개 들고 올 수 있도록 List로 받기
    List<ExhibitionTag> findTagByExhibitionId(Long exhibitionId);

    //태그 이름으로 전시 조회
    List<ExhibitionTag> findByTag_TagName(String tagName);

    //태그 id로 전시 조회
    List<ExhibitionTag>findExhibitionId_ByTagId(Long TagId);

//전시 전체 태그 조회
  List<ExhibitionTag> findAllByExhibition(Exhibition exhibition);
}