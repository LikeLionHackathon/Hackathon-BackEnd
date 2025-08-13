package com.Hackathon.glow.UserPreference.repository;

import com.Hackathon.glow.User.domain.User;
import com.Hackathon.glow.UserPreference.domain.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    //유저의 취향 조회
    Optional<UserPreference> findByUser_UserId(Long userId);

    Optional<UserPreference>findByUser(User user);

    //유저 한명의 각각의 질문에 대한 취향 조회 ..?
    //List<UserPreference> findByUserPreferenceId
}
