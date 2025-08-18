package com.Hackathon.glow.user.dto;


import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.user.domain.UserType;
import com.Hackathon.glow.userpreference.domain.UserPreference;
import com.Hackathon.glow.userpreference.dto.UserPreferenceResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String profile;
    private UserType userType;

    private UserPreferenceResponseDto userPreference;

    public static UserResponse from(User user,UserPreference userPreference) {
        if(user == null) return null;

        return UserResponse.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .profile(user.getProfile())
                .userType(user.getUserType())
                .userPreference(UserPreferenceResponseDto.from(userPreference))
                .build();

    }

}
