package com.Hackathon.glow.artistexhibition.repository;

import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistExhibitionRepository extends JpaRepository<ArtistExhibition, Long> {

    List<ArtistExhibition> findByExhibition_Id(Long exhibitionId);
}
