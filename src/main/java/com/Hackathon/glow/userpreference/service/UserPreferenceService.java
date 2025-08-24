package com.Hackathon.glow.userpreference.service;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.exhibition.domain.Exhibition;
import com.Hackathon.glow.tag.repository.ExhibitionTagRepository;
import com.Hackathon.glow.tag.repository.TagRepository;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import com.Hackathon.glow.user.repository.UserRepository;
import com.Hackathon.glow.userpreference.domain.UserPreference;
import com.Hackathon.glow.userpreference.dto.Preference;
import com.Hackathon.glow.userpreference.dto.PrefrenceAnswerRequest;
import com.Hackathon.glow.userpreference.dto.SimpleResponse;
import com.Hackathon.glow.userpreference.dto.ThemeTagExhibitionDto;
import com.Hackathon.glow.userpreference.dto.UserPreferenceRequest;
import com.Hackathon.glow.userpreference.repository.UserPreferenceRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceService {

    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;

    private final UserPreferenceRepository userPreferenceRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    public void createPreference(UserPreferenceRequest request, HttpSession session) {
        User loginUser = authService.getLoginUser(session);
        if (request.getUserType() == 1) {
            loginUser.setUserType(UserType.GLOW);
            userRepository.save(loginUser);
        }
        List<PrefrenceAnswerRequest> preferenceAnswers = request.getPreferenceAnswers();
        for (PrefrenceAnswerRequest answerRequest : preferenceAnswers) {
            int questionId = answerRequest.getQuestionId();
            List<Integer> answerIds = answerRequest.getAnswerId();
            for (int a_id : answerIds) {
                userPreferenceRepository.save(new UserPreference(loginUser, questionId, a_id));
            }
        }
    }

    public ThemeTagExhibitionDto getByUserThemeTag(HttpSession session) {
        User user = authService.getLoginUser(session);
        List<UserPreference> userPreferences = userPreferenceRepository.findByUser_UserId(
            user.getUserId());

        List<Integer> themeAnswerIds = userPreferences.stream()
            .filter(uf -> uf.getQuestionId() == 1)
            .map(uf -> uf.getAnswerId()).toList();

        List<String> themeTagNames = new ArrayList<>();
        for (int i : themeAnswerIds) {
            themeTagNames.add(Preference.answers[1][i]);
        }

        String randomTag = "";
        if (!themeTagNames.isEmpty()) {
            int randomIndex = (int) (Math.random() * themeTagNames.size());
            randomTag = themeTagNames.get(randomIndex);
            System.out.println("랜덤 태그: " + randomTag);
        } else {
            System.out.println("리스트가 비어 있습니다.");
        }

        List<Exhibition> exibitions = exhibitionTagRepository.findByTag_TagName(randomTag).stream()
            .map(et -> et.getExhibition()).toList();

        ThemeTagExhibitionDto response = new ThemeTagExhibitionDto();
        response.setTag(randomTag);

        List<SimpleResponse> simpleResponses = new ArrayList<>();
        for (Exhibition e : exibitions) {
            List<String> tags = exhibitionTagRepository.findByExhibition_Id(
                e.getId()).stream().map(et -> et.getTag().getTagName()).toList();

            simpleResponses.add(new SimpleResponse(e, tags));
        }
        response.setExhibitions(simpleResponses);
        return response;
    }

    public ThemeTagExhibitionDto getByUserMoodTag(HttpSession session) {
        User user = authService.getLoginUser(session);
        List<UserPreference> userPreferences = userPreferenceRepository.findByUser_UserId(
            user.getUserId());

        List<Integer> themeAnswerIds = userPreferences.stream()
            .filter(uf -> uf.getQuestionId() == 3)
            .map(uf -> uf.getAnswerId()).toList();

        List<String> themeTagNames = new ArrayList<>();
        for (int i : themeAnswerIds) {
            if (i == 1) {
                themeTagNames.add("차분한");
                themeTagNames.add("잔잔한");
            } else if (i == 2) {
                themeTagNames.add("웅장한");
                themeTagNames.add("강렬한");
            } else if (i == 3) {
                themeTagNames.add("감성자극");
                themeTagNames.add("힐링");
                themeTagNames.add("몽환적");
            } else if (i == 4) {
                themeTagNames.add("신비로운");
                themeTagNames.add("감각적인");
            }
        }

        String randomTag = "";
        if (!themeTagNames.isEmpty()) {
            int randomIndex = (int) (Math.random() * themeTagNames.size());
            randomTag = themeTagNames.get(randomIndex);
            System.out.println("랜덤 태그: " + randomTag);
        } else {
            System.out.println("리스트가 비어 있습니다.");
        }

        List<Exhibition> exibitions = exhibitionTagRepository.findByTag_TagName(randomTag).stream()
            .map(et -> et.getExhibition()).toList();

        ThemeTagExhibitionDto response = new ThemeTagExhibitionDto();
        response.setTag(randomTag);

        List<SimpleResponse> simpleResponses = new ArrayList<>();
        for (Exhibition e : exibitions) {
            List<String> tags = exhibitionTagRepository.findByExhibition_Id(
                e.getId()).stream().map(et -> et.getTag().getTagName()).toList();

            simpleResponses.add(new SimpleResponse(e, tags));
        }
        response.setExhibitions(simpleResponses);
        return response;
    }


    public List<String> getUsersTag(HttpSession session) {
        User loginUser = authService.getLoginUser(session);
        List<String> response = new ArrayList<>();
        List<UserPreference> userPreferences = userPreferenceRepository.findByUser(loginUser);
        for (UserPreference uf : userPreferences) {
            if (uf.getQuestionId() != 3) {
                response.add(uf.toString());
            } else {
                int i = uf.getAnswerId();
                if (i == 1) {
                    response.add("차분한");
                    response.add("잔잔한");
                } else if (i == 2) {
                    response.add("웅장한");
                    response.add("강렬한");
                } else if (i == 3) {
                    response.add("감성자극");
                    response.add("힐링");
                    response.add("몽환적");
                } else if (i == 4) {
                    response.add("신비로운");
                    response.add("감각적인");
                }
            }
        }
        return response;
    }
}
