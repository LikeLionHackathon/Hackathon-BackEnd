package com.Hackathon.glow.exhibitionspace.service;

import com.Hackathon.glow.exhibitionspace.domain.ExhibitionSpace;
import com.Hackathon.glow.exhibitionspace.dto.ExhibitionSpaceRequestDto;
import com.Hackathon.glow.exhibitionspace.dto.ExhibitionSpaceResponseDto;
import com.Hackathon.glow.exhibitionspace.repository.ExhibitionSpaceRepository;
import com.Hackathon.glow.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ExhibitionSpaceService {

    private final ExhibitionSpaceRepository exhibitionSpaceRepository;

    //전시공간 등록
    @Transactional
    public ExhibitionSpaceResponseDto createSpace(ExhibitionSpaceRequestDto request, User user)
    {
        ExhibitionSpace space = ExhibitionSpace.builder()
                .location(request.getLocation())
                .size(request.getSize())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .cost(request.getCost())
                .imageUrl(request.getImageUrl())
                .owner(user)// 로그인 세션에서 받는거
                .build();

        //save 메서드는 반드시 entity 값만 바듬
        ExhibitionSpace saved = exhibitionSpaceRepository.save(space);

        return ExhibitionSpaceResponseDto.from(saved);

    }

    //전시공간 단일 조회 ( by id )
    @Transactional(readOnly = true)
    public ExhibitionSpaceResponseDto getSpace(Long id)
    {
        ExhibitionSpace space =exhibitionSpaceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 공실을 찾을 수 없습니다."));
        return ExhibitionSpaceResponseDto.from(space);
    }

    //전시공간 전체 조회

    @Transactional(readOnly = true)
    public List<ExhibitionSpaceResponseDto> getAllSpaces()
    {
        return exhibitionSpaceRepository.findAll().stream()
                .map(ExhibitionSpaceResponseDto::from)
                .toList();

    }

    //전시공간 삭제
    @Transactional
    public void deleteSpace (Long id,User user)
    {
        ExhibitionSpace space = exhibitionSpaceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 공실을 찾을 수 없습니다. "));

        //소유자 본인만 삭제 가능 !
        if(!space.getOwner().getUserId().equals(user.getUserId()))
        {
            throw new IllegalStateException("본인 소유의 공실만 삭제할 수 있습니다.");
        }
        exhibitionSpaceRepository.delete(space);
    }

    //전시공간 수정
    public ExhibitionSpaceResponseDto updateSpace(Long id, ExhibitionSpaceRequestDto request, User user)
    {
        ExhibitionSpace space = exhibitionSpaceRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 공실을 찾을 수 없습니다."));

        //소유자 검증
        if(!space.getOwner().getUserId().equals(user.getUserId()))
        {
            throw new IllegalStateException("본인 소유의 공실만 수정할 수 있습니다.");
        }

        //값 수정
        space.setLocation(request.getLocation());
        space.setSize(request.getSize());
        space.setDescription(request.getDescription());
        space.setStartDate(request.getStartDate());
        space.setEndDate(request.getEndDate());
        space.setCost(request.getCost());
        space.setImageUrl(request.getImageUrl());

        return ExhibitionSpaceResponseDto.from(space);

    }


}


