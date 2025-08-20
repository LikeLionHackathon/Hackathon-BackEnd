package com.Hackathon.glow.exhibition.controller;


import com.Hackathon.generic.util.MultipartInputStreamFileResource;
import com.Hackathon.glow.exhibition.dto.ExhibitionRequest;
import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.service.ExhibitionService;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
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
        @RequestPart("data") ExhibitionRequest request,     // JSON 자동 매핑
        @RequestPart(value = "posterImage",required = false) MultipartFile posterImage,
        @RequestPart(value = "artworkImages",required = false) List<MultipartFile> artworkImages
    ) {

        return ResponseEntity.ok(exhibitionService.register(request, posterImage, artworkImages));
    }

    @PostMapping(value = "/recommend", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> recommendExhibition(
        @RequestPart("text") String text,
        @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        String pythonUrl = "http://localhost:8000/recommend";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("text", text);

        if (images != null) {
            for (MultipartFile file : images) {
                body.add("artworkImages",
                    new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(pythonUrl, requestEntity, Map.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
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
    public ExhibitionResponse getExhibition(@PathVariable Long exhibitionId)
    {
        return exhibitionService.getExhibition(exhibitionId);
    }
}
