package com.Hackathon.glow.user.repository;

import com.Hackathon.glow.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByLoginId(String loginId);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByNickname(String nickname);
}
