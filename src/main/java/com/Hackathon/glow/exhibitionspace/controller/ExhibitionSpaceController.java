package com.Hackathon.glow.exhibitionspace.controller;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.exhibitionspace.domain.ExhibitionSpace;
import com.Hackathon.glow.exhibitionspace.dto.ExhibitionSpaceRequestDto;
import com.Hackathon.glow.exhibitionspace.dto.ExhibitionSpaceResponseDto;
import com.Hackathon.glow.exhibitionspace.service.ExhibitionSpaceService;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/spaces")
@RequiredArgsConstructor

public class ExhibitionSpaceController {

    private final ExhibitionSpaceService exhibitionSpaceService;
    private final AuthService authService;
    //전시 공간 등록
    @PostMapping
    public ResponseEntity<ExhibitionSpaceResponseDto> createSpace(@RequestBody ExhibitionSpaceRequestDto requestDto, HttpSession session)
    {
//로그인 기능 없으니까 나중에 파라미터에 @AuthenticationPrincipal User user 넣기.
        User dummyUser = authService.getLoginUser(session);

        return ResponseEntity.ok(exhibitionSpaceService.createSpace(requestDto, dummyUser));
    }


    //전시 공간 단건 조회
    @GetMapping("/{spaceId}")
    public ResponseEntity<ExhibitionSpaceResponseDto> getSpace (@PathVariable Long spaceId)
    {
        return ResponseEntity.ok(exhibitionSpaceService.getSpace(spaceId));
    }

    //전시 공간 전체 조회
    @GetMapping
    public ResponseEntity<List<ExhibitionSpaceResponseDto>> getAllSpaces()
    {
        return ResponseEntity.ok(exhibitionSpaceService.getAllSpaces());
    }

    //전시 공간 삭제
    @DeleteMapping("/{spaceId}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long spaceId, HttpSession session)
    {
//일단 dummyUser로 !!!
        User dummyUser = authService.getLoginUser(session);
        exhibitionSpaceService.deleteSpace(spaceId, dummyUser);
        return ResponseEntity.noContent().build();
    }

    //전시 공간 수정
    @PutMapping("/{spaceId}")
    public ResponseEntity<ExhibitionSpaceResponseDto> updateSpace(
            @PathVariable Long spaceId,
            @RequestBody ExhibitionSpaceRequestDto requestDto,
        HttpSession session
    )
    {
        //더미 유저 동일하게 사용..
        User dummyUser = authService.getLoginUser(session);
        return ResponseEntity.ok(exhibitionSpaceService.updateSpace(spaceId, requestDto, dummyUser));
    }


}
