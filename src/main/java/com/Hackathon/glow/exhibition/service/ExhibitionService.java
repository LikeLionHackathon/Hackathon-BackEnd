package com.Hackathon.glow.exhibition.service;

import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.ExhibitionArtwork;
import com.Hackathon.glow.tag.domain.ExhibitionTag;
import com.Hackathon.glow.tag.domain.Tag;
import com.Hackathon.glow.tag.repository.ExhibitionTagRepository;
import com.Hackathon.glow.tag.repository.TagRepository;
import com.Hackathon.glow.artwork.repository.ArtworkRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.repository.ExhibitionArtworkRepository;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionArtworkRepository exhibitionArtworkRepository;
    private final ArtworkRepository artworkRepository;
    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;

    //전시 등록
    /*@Transactional
    public ExhibitionResponse createExhibition(ExhibitionRequest exhibitionRequest){
        Exhibition ex =exhibitionRepository.save(exhibitionRequest.toEntity());
        Artwork artwork=artworkRepository.save()
                Tag tag=
        return ExhibitionResponse.from(ex,art);
    }
*/

    //전시 개별 조회 (by id)
    @Transactional(readOnly = true)
    public ExhibitionResponse getExhibition(Long ExhibitionId)
    {
        Exhibition exhibition = exhibitionRepository.findById(ExhibitionId)
                .orElseThrow(()->new IllegalArgumentException("해당 전시를 찾을 수 없습니다."));

        List<Artwork> artworks =exhibitionArtworkRepository.findAllByExhibition(exhibition).stream().map(ExhibitionArtwork::getArtwork).toList();

        List<Tag> tags = exhibitionTagRepository.findByExhibition_Id(ExhibitionId).stream()
                .map(ExhibitionTag::getTag)
                .toList();
        return ExhibitionResponse.from(exhibition,artworks,tags);
    }


    //전시 등록정보 전체 조회


    //전시 등록정보 전체 조회 (등록된 날짜 순으로 )
    public List<ExhibitionResponse> getExhibitions(String sortKey,String direction)
    {
        String sortProperty=(sortKey==null||sortKey.isBlank())? "registeredDate" : sortKey;
        Sort.Direction dir = ("ASC".equalsIgnoreCase(direction)) ? Sort.Direction.ASC : Sort.Direction.DESC;

        List<Exhibition> exhibitions = exhibitionRepository.findAll(Sort.by(dir,sortProperty));
        return exhibitions.stream()
                .map(ex -> {
                    // 작품 뽑기: ExhibitionArtwork -> Artwork
                    List<Artwork> artworks = exhibitionArtworkRepository.findAllByExhibition(ex).stream()
                            .map(ExhibitionArtwork::getArtwork)
                            .toList();

                    // 태그 뽑기: ExhibitionTag -> Tag
                    List<Tag> tags = exhibitionTagRepository.findByExhibition_Id(ex.getId()).stream()
                            .map(ExhibitionTag::getTag)
                            .toList();

                    return ExhibitionResponse.from(ex, artworks, tags);
                })
                .toList();
    }


    //전시 정보 수정

    //전시 작품 삭제 / 수정


}
