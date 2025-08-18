package com.Hackathon.glow.userpreference.repository;

import com.Hackathon.glow.userpreference.domain.PreferenceAnswer;
import com.Hackathon.glow.userpreference.domain.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceAnswerRepository extends JpaRepository<PreferenceAnswer, Long> {

    //특정 userpreference에 연결된 모든 preferenceAnswer 조회
    //객체 받아서 answer 리스트 가져오기
    List<PreferenceAnswer> findByUserPreference(UserPreference userPreference);
}
