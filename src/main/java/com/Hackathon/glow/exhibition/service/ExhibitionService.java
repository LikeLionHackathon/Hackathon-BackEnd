package com.Hackathon.glow.exhibition.service;

import com.Hackathon.generic.exception.AiResponseException;
import com.Hackathon.generic.s3.exception.S3Exception;
import com.Hackathon.generic.s3.service.S3ClientService;
import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.ExhibitionArtwork;
import com.Hackathon.glow.exhibition.dto.AiTagRequeset;
import com.Hackathon.glow.exhibition.dto.AiTagResponse;
import com.Hackathon.glow.exhibition.dto.ExhibitionRequest;
import com.Hackathon.glow.tag.domain.ExhibitionTag;
import com.Hackathon.glow.tag.domain.Tag;
import com.Hackathon.glow.tag.repository.ExhibitionTagRepository;
import com.Hackathon.glow.tag.repository.TagRepository;
import com.Hackathon.glow.artwork.repository.ArtworkRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.repository.ExhibitionArtworkRepository;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import java.io.IOException;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionArtworkRepository exhibitionArtworkRepository;
    private final ArtworkRepository artworkRepository;
    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final S3ClientService s3ClientService;
    private final RestTemplate restTemplate;

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



    public Long register(ExhibitionRequest exhibitionRequest, MultipartFile posterImage, List<MultipartFile> artworkImages) {
        //aws 이미지 등록
        String postImageUrl;
        List<String> artworkImageUrls;
        try {
            postImageUrl = s3ClientService.upload(posterImage);
            artworkImageUrls = s3ClientService.uploadFiles(
                artworkImages);
        } catch (IOException e) {
            throw new S3Exception(e.getMessage());
        }
        //전시 엔티티 등록
        Exhibition exhibition = exhibitionRequest.toEntity(postImageUrl);
        Exhibition save = exhibitionRepository.save(exhibition);
        //작품 등록
        List<Artwork> artworks = new ArrayList<>();
        artworkImageUrls.stream().forEach(url ->{
            Artwork artwork = new Artwork(url);
            artworks.add(artworkRepository.save(artwork));
        });

        //전시_작품 등록
        for (Artwork artwork : artworks) {
            ExhibitionArtwork exhibitionArtwork = new ExhibitionArtwork(save, artwork);
            exhibitionArtworkRepository.save(exhibitionArtwork);
        }

        //ai 태그 받아오기
        List<Tag> tags= getTag(new AiTagRequeset(save, postImageUrl, artworkImageUrls));
        //전시_태그 저장
        for (Tag tag : tags) {
            if (!tagRepository.existsByTagName(tag.getTagName())) {
                Tag saved = tagRepository.save(tag);
                ExhibitionTag exhibitionTag = new ExhibitionTag(exhibition, saved);
                exhibitionTagRepository.save(exhibitionTag);
            }
        }
        return exhibition.getId();
    }

    @Value("${ai.server.url}")
    private String aiServerUrl;

    private List<Tag> getTag(AiTagRequeset request) {
        ResponseEntity<AiTagResponse> response = restTemplate.postForEntity(
            aiServerUrl+"/tags",   // yml에서 불러온 값
            request,
            AiTagResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getTags().stream().map(Tag::new).toList();
        } else {
            throw new AiResponseException("AI 서버 태그 응답 실패: " + response.getStatusCode());
        }
    }
}
