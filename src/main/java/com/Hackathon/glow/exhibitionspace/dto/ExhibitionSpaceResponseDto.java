package com.Hackathon.glow.exhibitionspace.dto;

import com.Hackathon.glow.exhibitionspace.domain.ExhibitionSpace;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExhibitionSpaceResponseDto {

    //공실 id
    private Long id;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long cost;
    private Double size;
    private String description;
    private String imageUrl;

    //주인 정보
    private Long ownerId;
    private String ownerName;

    //entity -> responseDTO 변환
    public static ExhibitionSpaceResponseDto from(ExhibitionSpace space) {
        return ExhibitionSpaceResponseDto.builder()
                .id(space.getId())
                .location(space.getLocation())
                .startDate(space.getStartDate())
                .endDate(space.getEndDate())
                .cost(space.getCost())
                .size(space.getSize())
                .description(space.getDescription())
                .imageUrl(space.getImageUrl())
                .ownerId(space.getOwner() != null ? space.getOwner().getUserId() : null)
                .ownerName(space.getOwner() != null ? space.getOwner().getUsername():null) // User 엔티티에서 닉네임 or 이름 가져오기
                .build();
    }

}
