package com.Hackathon.glow.userpreference.dto;

import com.Hackathon.glow.exhibition.dto.ExhibitionResponse;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ThemeTagExhibitionDto {

    String tag;
    List<SimpleResponse> exhibitions;
}
