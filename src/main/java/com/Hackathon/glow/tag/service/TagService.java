package com.Hackathon.glow.tag.service;

import com.Hackathon.glow.tag.domain.ExhibitionTag;
import com.Hackathon.glow.tag.domain.Tag;
import com.Hackathon.glow.tag.dto.ExhibitionTagResponse;
import com.Hackathon.glow.tag.repository.ExhibitionTagRepository;
import com.Hackathon.glow.tag.repository.TagRepository;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    /**
     * 전시 저장시 태그 받아오는 함수
     * 이 후 전시 조회시에는 디비에서 조회해서 가져오면 됨
     * @param exhibition
     * @return
     */
    public List<Tag> getTagByExhibitionInfo(Exhibition exhibition) {
        Tag mockTag1, mockTag2;
        if (tagRepository.findByTagName("따스한").isEmpty()) {
            mockTag1 = new Tag("따스한");
            mockTag1 = tagRepository.save(mockTag1);
        }
        if (tagRepository.findByTagName("초현실").isEmpty()) {
            mockTag2 = new Tag("초현실");
            mockTag2 = tagRepository.save(mockTag2);
        }
        mockTag1 = tagRepository.findByTagName("따스한").get();
        mockTag2 = tagRepository.findByTagName("초현실").get();
        ExhibitionTag exhibitionTag1 = new ExhibitionTag(exhibition, mockTag1);
        ExhibitionTag exhibitionTag2 = new ExhibitionTag(exhibition, mockTag2);

        exhibitionTagRepository.save(exhibitionTag1);
        exhibitionTagRepository.save(exhibitionTag2);

        List<Tag> result = new ArrayList<>();
        result.add(mockTag1);
        result.add(mockTag2);
        return result;
    }

    //전시 id로 태그 조회
    public List<ExhibitionTagResponse> getTagsByExhibitionId(Long exhibitionId) {
        return exhibitionTagRepository.findByExhibition_Id(exhibitionId)
                .stream()
                .map(ExhibitionTagResponse::from)
                .toList();
    }

    //태그 이름으로 전시 조회
    public List<Exhibition> getExhibitionsByTagName(String tagName) {
        return exhibitionTagRepository.findByTag_TagName(tagName)
                .stream()
                .map(ExhibitionTag::getExhibition)
                .toList();
    }
}