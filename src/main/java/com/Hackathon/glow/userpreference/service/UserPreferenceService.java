package com.Hackathon.glow.userpreference.service;

import com.Hackathon.generic.login.auth.AuthService;
import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.userpreference.domain.UserPreference;
import com.Hackathon.glow.userpreference.dto.PrefrenceAnswerRequest;
import com.Hackathon.glow.userpreference.dto.UserPreferenceRequest;
import com.Hackathon.glow.userpreference.repository.UserPreferenceRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceService {

    private UserPreferenceRepository userPreferenceRepository;
    private AuthService authService;

    public void createPreference(UserPreferenceRequest request, HttpSession session) {
        User loginUser = authService.getLoginUser(session);
        List<PrefrenceAnswerRequest> preferenceAnswers = request.getPreferenceAnswers();
        for (PrefrenceAnswerRequest answerRequest : preferenceAnswers) {
            answerRequest.getAnswerId().forEach(a_id -> {
                userPreferenceRepository.save(
                    new UserPreference(loginUser, answerRequest.getQuestionId(), a_id));
            });
        }
    }
}
