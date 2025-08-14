package com.Hackathon.glow.Tag.repository;

import com.Hackathon.glow.Tag.domain.Tag;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
//    List<Tag> findAllByExhibition(Exhibition exhibition);
}
