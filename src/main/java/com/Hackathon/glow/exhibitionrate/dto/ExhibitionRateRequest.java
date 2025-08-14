package com.Hackathon.glow.exhibitionrate.dto;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.exhibitionrate.domain.ExhibitionRate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionRateRequest {

    //별점

    @NotNull
    @Min(1) @Max(5)
    private Long rate;

    //한줄평
    @Size(max = 500)
    private String content;


    private Long userId;      // 작성자 ID
    private Long exhibitionId;// 전시 ID

    public ExhibitionRate toEntity(User user, Exhibition exhibition) {
        return ExhibitionRate.builder()
                .rate(this.rate)
                .content(this.content)
                .user(user)
                .exhibition(exhibition)
                .build();
    }
}
