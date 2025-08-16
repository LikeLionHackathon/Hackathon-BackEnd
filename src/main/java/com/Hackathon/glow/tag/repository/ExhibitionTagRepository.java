package com.Hackathon.glow.tag.repository;

import com.Hackathon.glow.tag.domain.ExhibitionTag;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag, Long> {
//전시 ID로 exhibitionTag 조회
    List<ExhibitionTag> findByExhibition_Id(Long exhibitionId);

    // 태그 이름으로 exhibitionTag 조회
    List<ExhibitionTag> findByTag_TagName(String tagName);

    // 태그 ID로 exhibitionTag 조회
    List<ExhibitionTag> findByTag_Id(Long tagId);

    List<ExhibitionTag>findExhibitionId_ByTagId(Long TagId);

//전시 전체 태그 조회
  List<ExhibitionTag> findAllByExhibition(Exhibition exhibition);
}