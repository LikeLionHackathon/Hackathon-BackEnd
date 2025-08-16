package com.Hackathon.glow.exhibition.controller;


import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import com.Hackathon.glow.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitions")
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

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
