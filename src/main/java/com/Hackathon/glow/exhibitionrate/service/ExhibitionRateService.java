package com.Hackathon.glow.exhibitionrate.service;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.User.repository.UserRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibition.repository.ExhibitionRepository;
import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;
import com.Hackathon.glow.exhibitionrate.dto.ExhibitionRateListResponse;
import com.Hackathon.glow.exhibitionrate.dto.ExhibitionRateRequest;
import com.Hackathon.glow.exhibitionrate.dto.ExhibitionRateResponse;
import com.Hackathon.glow.exhibitionrate.repository.ExhibitionRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ExhibitionRateService {
    private final ExhibitionRateRepository exhibitionRateRepository;
    private final UserRepository userRepository;
    private final ExhibitionRepository exhibitionRepository;

    //리뷰 생성

    public ExhibitionRateResponse createReview(ExhibitionRateRequest request)
    {
//작성자 조회 - 전시 조회 - 저장 -반환
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. id=" + request.getUserId()));

        Exhibition exhibition = exhibitionRepository.findById(request.getExhibitionId())
                .orElseThrow(() -> new IllegalArgumentException("전시를 찾을 수 없습니다. id=" + request.getExhibitionId()));


        ExhibitionRate saved = exhibitionRateRepository.save(
                ExhibitionRate.builder()
                        .rate(request.getRate())
                        .content(request.getContent())
                        .user(user)
                        .exhibition(exhibition)
                        .build()
        );

        return ExhibitionRateResponse.from(saved);
    }

    //리뷰 전체 조회

    @Transactional(readOnly = true)
    public ExhibitionRateListResponse getExhibitionRateList(Long exhibitionId)
    {
        //전시 조회
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new IllegalArgumentException("전시를 찾을 수 없습니다. id=" + exhibitionId));

        //리뷰 조회

        List<ExhibitionRate> rates = exhibitionRateRepository.findByExhibitionId(exhibitionId);

        //return
        return ExhibitionRateListResponse.from(exhibitionId, rates);
    }



}
