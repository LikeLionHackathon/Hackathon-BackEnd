package com.Hackathon.glow.exhibition.service;

import com.Hackathon.glow.Tag.domain.Tag;
import com.Hackathon.glow.Tag.repository.TagRepository;
import com.Hackathon.glow.artwork.repository.ArtworkRepository;
import com.Hackathon.glow.exhibition.dto.ExhibitionRequest;
import com.Hackathon.glow.exhibition.repository.ExhibitionArtworkRepository;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionArtworkRepository exhibitionArtworkRepository;
    private final ArtworkRepository artworkRepository;
    private final TagRepository tagRepository;


//    public Long createExhibition(ExhibitionRequest exhibitionRequest) {
//        Tag tag  = getTag(exhibitionRequest.getDescription());
//
//    }
//    public Tag getTag(String description) {
////        return new Tag
//    }
}
