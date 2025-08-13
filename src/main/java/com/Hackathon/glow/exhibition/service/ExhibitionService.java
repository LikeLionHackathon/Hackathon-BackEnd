/*package com.Hackathon.glow.exhibition.service;

import com.Hackathon.glow.Tag.repository.TagRepository;
import com.Hackathon.glow.User.repository.UserRepository;
import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.artwork.repository.ArtworkRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.dto.ExhibitionRequest;
import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.repository.ExhibitionArtworkRepository;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionArtworkRepository exhibitionArtworkRepository;
    private final ArtworkRepository artworkRepository;
    private final TagRepository tagRepository;

    //전시 등록
    @Transactional
    public ExhibitionResponse createExhibition(ExhibitionRequest exhibitionRequest){
        Exhibition ex =exhibitionRepository.save(exhibitionRequest.toEntity());
        Artwork artwork=artworkRepository.save()
                Tag tag=
        return ExhibitionResponse.from(ex,art);
    }

    //전시 조회 (by id)
    public ExhibitionResponse getExhibition(String title)
    {
        Exhibition exhibition = exhibitionRepository.findByTitle(title)
                .orElseThrow(()->new IllegalArgumentException("잘못된 전시 제목입니다."));

    }
}
