package com.Hackathon.glow.artistexhibition.controller;


import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.artistexhibition.dto.ArtistExhibitionResponseDto;
import com.Hackathon.glow.artistexhibition.entity.ArtistExhibition;
import com.Hackathon.glow.artistexhibition.service.ArtistExhibitionService;
import com.Hackathon.glow.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exhibitionslist")
@RequiredArgsConstructor
public class ArtistExhibitionController {

    private final ArtistExhibitionService artistExhibitionService;
    private final AuthService authService;

    /*
    전시 제목
전시 startDate
전시 endDate
전시 posterImage
     */

    //로그인 유저의 전시 조회
    @GetMapping
    public ResponseEntity<List<ArtistExhibitionResponseDto>> getMyExhibitions(HttpSession httpSession) {
        //로그인 유저
        User user =authService.getLoginUser(httpSession);
        List<ArtistExhibitionResponseDto> exhibition= artistExhibitionService.getMyExhibitions(httpSession);
        return ResponseEntity.ok(exhibition);
    }


    //작가들의 전시 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<ArtistExhibitionResponseDto>> getMyExhibitions(@PathVariable Long userId) {

         List<ArtistExhibitionResponseDto> exhibitions= artistExhibitionService.getAllExhibitions(userId);
         return ResponseEntity.ok(exhibitions);
    }
}
