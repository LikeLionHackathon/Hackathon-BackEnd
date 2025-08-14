package com.Hackathon.glow.artwork.repository;

import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.domain.ExhibitionArtwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    //전시 제목으로 찾기
//    List<Artwork> findByExhibitiontitle(String title);

    //전시 id로 찾기
//    List<ExhibitionArtwork> findByExhibitionid(Long id);

    //전시 엔티티로 찾기
//    List<ExhibitionArtwork> findbyExhibition(Exhibition exhibition);

}
