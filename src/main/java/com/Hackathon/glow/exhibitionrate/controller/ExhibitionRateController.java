package com.Hackathon.glow.exhibitionrate.controller;

import com.Hackathon.glow.exhibitionrate.dto.ExhibitionRateListResponse;
import com.Hackathon.glow.exhibitionrate.dto.ExhibitionRateRequest;
import com.Hackathon.glow.exhibitionrate.dto.ExhibitionRateResponse;
import com.Hackathon.glow.exhibitionrate.service.ExhibitionRateService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exhibitions/{exhibitionId}/reviews")
@RequiredArgsConstructor
public class ExhibitionRateController {

    private final ExhibitionRateService exhibitionRateService;

    //전시별 리뷰 생성
    @PostMapping
    public ResponseEntity<ExhibitionRateResponse> createExhibitionRate(@RequestBody ExhibitionRateRequest exhibitionRateRequest) {
        ExhibitionRateResponse response=exhibitionRateService.createReview(exhibitionRateRequest);
        return ResponseEntity.ok(response);
    }



    //전시별 리뷰 조회
    @GetMapping
    public ResponseEntity<ExhibitionRateListResponse> getExhibitionRates(@PathVariable Long ExhibitionId)
    {
        ExhibitionRateListResponse response = exhibitionRateService.getExhibitionRateList(ExhibitionId);
        return ResponseEntity.ok(response);
    }


}
