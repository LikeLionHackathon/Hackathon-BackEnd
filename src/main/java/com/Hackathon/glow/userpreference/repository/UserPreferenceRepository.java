package com.Hackathon.glow.userpreference.repository;

import com.Hackathon.glow.user.domain.User;
import com.Hackathon.glow.userpreference.domain.UserPreference;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    //유저의 취향 조회
    Optional<UserPreference>findByUser(User user);

    boolean existsByUserUserIdAndQuestionId(Long userId, Long questionId);

    List<UserPreference> findByUser_UserId(Long userId);


}
