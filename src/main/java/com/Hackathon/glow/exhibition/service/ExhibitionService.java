package com.Hackathon.glow.exhibition.service;

import com.Hackathon.generic.exception.AiResponseException;
import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.generic.s3.exception.S3Exception;
import com.Hackathon.generic.s3.service.S3ClientService;
import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import com.Hackathon.glow.artistexhibition.repository.ArtistExhibitionRepository;
import com.Hackathon.glow.artwork.domain.Artwork;
import com.Hackathon.glow.exhibition.domain.ExhibitionArtwork;
import com.Hackathon.glow.exhibition.dto.*;
import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;
import com.Hackathon.glow.exhibitionrate.repository.ExhibitionRateRepository;
import com.Hackathon.glow.tag.domain.ExhibitionTag;
import com.Hackathon.glow.tag.domain.Tag;
import com.Hackathon.glow.tag.repository.ExhibitionTagRepository;
import com.Hackathon.glow.tag.repository.TagRepository;
import com.Hackathon.glow.artwork.repository.ArtworkRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.repository.ExhibitionArtworkRepository;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.dto.UserResponse;
import com.Hackathon.glow.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
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
    private final UserRepository userRepository;
    private final ArtistExhibitionRepository artistExhibitionRepository;
    private final AuthService authService;
    private final ExhibitionRateRepository exhibitionRateRepository;

    //전시 개별 조회 (by id)
    @Transactional(readOnly = true)
    public ExhibitionDetailResponse getExhibition(Long exhibitionid) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionid)
            .orElseThrow(() -> new IllegalArgumentException("해당 전시를 찾을 수 없습니다."));

        List<Artwork> artworks = exhibitionArtworkRepository.findAllByExhibition(exhibition)
            .stream().map(ExhibitionArtwork::getArtwork).toList();

        List<Tag> tags = exhibitionTagRepository.findByExhibition_Id(exhibitionid).stream()
            .map(ExhibitionTag::getTag)
            .toList();

        List<UserResponse> artists = artistExhibitionRepository.findByExhibition_Id(exhibitionid)
            .stream()
            .map(ae -> {
                return UserResponse.of(ae.getUser());
            }).toList();

        return ExhibitionDetailResponse.from(exhibition, artworks, tags, artists);
    }

    //전시 등록정보 전체 조회


    //전시 등록정보 전체 조회 (등록된 날짜 순으로 )
    public List<ExhibitionResponse> getExhibitions(String sortKey, String direction) {
        String sortProperty = (sortKey == null || sortKey.isBlank()) ? "registeredDate" : sortKey;
        Sort.Direction dir =
            ("ASC".equalsIgnoreCase(direction)) ? Sort.Direction.ASC : Sort.Direction.DESC;

        List<Exhibition> exhibitions = exhibitionRepository.findAll(Sort.by(dir, sortProperty));
        return exhibitions.stream()
            .map(ex -> {
                // 작품 뽑기: ExhibitionArtwork -> Artwork
                List<Artwork> artworks = exhibitionArtworkRepository.findAllByExhibition(ex)
                    .stream()
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


    public Long register(ExhibitionRequest exhibitionRequest, MultipartFile posterImage,
        List<MultipartFile> artworkImages,
        HttpSession session) {
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
        artworkImageUrls.stream().forEach(url -> {
            Artwork artwork = new Artwork(url);
            artworks.add(artworkRepository.save(artwork));
        });

        //전시_작품 등록
        for (Artwork artwork : artworks) {
            ExhibitionArtwork exhibitionArtwork = new ExhibitionArtwork(save, artwork);
            exhibitionArtworkRepository.save(exhibitionArtwork);
        }

        //함께하는 작가 등록
        List<User> artists = new ArrayList<>();
        artists.add(authService.getLoginUser(session));
        exhibitionRequest.getArtists().stream()
            .forEach(artistId -> {
                User user = userRepository.findByUserId(artistId)
                    .orElseThrow(
                        () -> new IllegalStateException("artistId와 일치하는 artist가 존재하지 않습니다"));
                artists.add(user);
            });

        for (User artist : artists) {
            artistExhibitionRepository.save(new ArtistExhibition(artist, save));
        }

        //ai 태그 받아오기
        List<Tag> tags = getTag(new AiTagRequeset(save, postImageUrl, artworkImageUrls));
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
            aiServerUrl + "/tags",   // yml에서 불러온 값
            request,
            AiTagResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getTags().stream().map(Tag::new).toList();
        } else {
            throw new AiResponseException("AI 서버 태그 응답 실패: " + response.getStatusCode());
        }
    }

    public List<VisitedExhibitionDto> getVisitedExhibition(HttpSession session) {
        User user = authService.getLoginUser(session);
        return exhibitionRateRepository.findByUser_UserId(
                user.getUserId())
            .stream()
            .map(er -> {
                Exhibition e = er.getExhibition();
                List<UserResponse> artists = artistExhibitionRepository.findByExhibition_Id(
                        e.getId())
                    .stream().map(artistExhibition -> UserResponse.of(artistExhibition.getUser()))
                    .toList();
                return new VisitedExhibitionDto(e, artists);
            }).toList();
    }


    //전시 검색
    public List<ExhibitionSearchResponse> getSearchedExhibitions(String title)
    {
        List<Exhibition> exhibitions = exhibitionRepository.findByTitleContaining(title);


        return exhibitions.stream().map(ExhibitionSearchResponse::from)
                .collect(Collectors.toList());
    }

//    //진행중인 전시 조회
//    public ExhibitionResponse getExhibitionByRegisteredDate(LocalDate registeredDate)
//    {
//
//    }
}
