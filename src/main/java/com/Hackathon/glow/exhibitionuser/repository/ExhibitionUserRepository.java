package com.Hackathon.glow.exhibitionuser.repository;

import com.Hackathon.glow.exhibitionuser.domain.ExhibitionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExhibitionUserRepository extends JpaRepository<ExhibitionUser, Long> {

    //특정 유저 id에 해당하는 모든 방문 기록을 조회
    List<ExhibitionUser> findByUserId(Long userId);

}
