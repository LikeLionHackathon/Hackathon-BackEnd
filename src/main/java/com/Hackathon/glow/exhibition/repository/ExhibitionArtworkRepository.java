package com.Hackathon.glow.exhibition.repository;

import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.domain.ExhibitionArtwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExhibitionArtworkRepository extends JpaRepository<ExhibitionArtwork, Long> {
    List<ExhibitionArtwork> findAllByExhibition(Exhibition exhibition);

}