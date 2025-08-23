package com.Hackathon.glow.exhibition.controller;


import com.Hackathon.generic.util.MultipartInputStreamFileResource;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.dto.ExhibitionDetailResponse;
import com.Hackathon.glow.exhibition.dto.ExhibitionRequest;
import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.dto.RecommendDto;
import com.Hackathon.glow.exhibition.dto.RecommendListDto;
import com.Hackathon.glow.exhibition.service.ExhibitionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/exhibitions")
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createExhibition(
        @Valid @RequestPart("data") ExhibitionRequest request,     // JSON 자동 매핑
        @RequestPart(value = "posterImage",required = false) MultipartFile posterImage,
        @RequestPart(value = "artworkImages",required = false) List<MultipartFile> artworkImages,
        HttpSession session
    ) {

        return ResponseEntity.ok(exhibitionService.register(request, posterImage, artworkImages,session));
    }

    @PostMapping(value = "/recommend", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecommendListDto> recommendExhibition(
        @RequestPart(value = "text", required = false) String text,
        // 클라이언트가 images로 보낸다 가정. (서버→파이썬 전송 시에는 artworkImages로 바꿔서 전달)
        @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        // ⚠️ 파이썬이 같은 머신이 아니라면 localhost 대신 EC2/도메인으로 바꾸세요.
        String pythonUrl = "http://localhost:8000/recommend";

        // ---- 멀티파트 바디 구성 ----
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // text 파트 (선택)
        if (text != null) {
            HttpHeaders textHeaders = new HttpHeaders();
            textHeaders.setContentType(MediaType.TEXT_PLAIN);
            // name은 HttpEntity가 아니라 body.add의 key로 지정됨
            HttpEntity<String> textEntity = new HttpEntity<>(text, textHeaders);
            body.add("text", textEntity);
        }

        // 이미지 파트들 → FastAPI 필드명은 artworkImages
        if (images != null) {
            for (MultipartFile file : images) {
                String filename = file.getOriginalFilename();
                String contentType = (file.getContentType() != null) ? file.getContentType()
                    : MediaType.APPLICATION_OCTET_STREAM_VALUE;

                // 파일 리소스 (filename 노출 위해 오버라이드)
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return filename;
                    }
                };

                HttpHeaders fileHeaders = new HttpHeaders();
                fileHeaders.setContentType(MediaType.parseMediaType(contentType));
                fileHeaders.setContentDisposition(
                    ContentDisposition.builder("form-data")
                        .name("artworkImages")     // ← FastAPI가 기대하는 필드명
                        .filename(filename)
                        .build()
                );

                HttpEntity<Resource> filePart = new HttpEntity<>(resource, fileHeaders);
                body.add("artworkImages", filePart);
            }
        }

        // ---- 요청 전송 ----
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 타임아웃 있는 RestTemplate 권장
        RestTemplate restTemplate = new RestTemplateBuilder()
            .build();

        // 파이썬은 List[Exhibition]을 반환 → Java에서는 Exhibition[]로 받고 리스트로 변환
        ResponseEntity<RecommendListDto> response =
            restTemplate.postForEntity(pythonUrl, requestEntity, RecommendListDto.class);

        return response;
    }

    //전시 전체 조회
    ///api/v1/exhibitions?sort=registeredDate&direction=DESC
    @GetMapping
    public List<ExhibitionResponse> getExhibitions( @RequestParam(value = "sort", required = false) String sort,
                                                    @RequestParam(value = "direction", required = false) String direction)
    {
        return exhibitionService.getExhibitions(sort,direction);
    }


    //전시 개별 조회 ( by id )
    @GetMapping ("/{exhibitionId}")
    public ExhibitionDetailResponse getExhibition(@PathVariable Long exhibitionId)
    {
        return exhibitionService.getExhibition(exhibitionId);
    }
}
