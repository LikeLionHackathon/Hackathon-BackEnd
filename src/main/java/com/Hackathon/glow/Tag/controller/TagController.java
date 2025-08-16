package com.Hackathon.glow.Tag.controller;

import com.Hackathon.glow.Tag.domain.Tag;
import com.Hackathon.glow.Tag.service.TagService;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitions/tags")
@RequiredArgsConstructor

public class TagController {
private final TagService tagService;
    //전시 id로 태그 조회
    @GetMapping("/exhibition/{exhibitionId}")
    public List<Tag> getTagsByExhibitionId(@PathVariable Long exhibitionId) {
        return tagService.getTagsByExhibitionId(exhibitionId);
    }


    //태그 이름으로 전시 조회
    @GetMapping("/name/{tagName}")
    public List<Exhibition> getExhibitionsByTagName(@PathVariable String tagName) {
        return tagService.getExhibitionsByTagName(tagName);
    }

}
