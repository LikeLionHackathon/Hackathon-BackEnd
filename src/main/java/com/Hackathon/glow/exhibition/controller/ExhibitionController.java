package com.Hackathon.glow.exhibition.controller;


import com.Hackathon.glow.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

}
