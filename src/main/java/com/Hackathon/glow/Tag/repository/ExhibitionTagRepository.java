package com.Hackathon.glow.Tag.repository;

import com.Hackathon.glow.Tag.domain.ExhibitionTag;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag, Long> {
//    List<ExhibitionTag> findAllByExhibition(Exhibition exhibition);
}
