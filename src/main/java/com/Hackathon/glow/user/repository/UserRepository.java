package com.Hackathon.glow.user.repository;

import com.Hackathon.glow.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
//유저 찾기 ( id 로 )
    Optional<User> findByUserId(Long userId);

}
