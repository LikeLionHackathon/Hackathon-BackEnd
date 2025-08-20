package com.Hackathon.glow.spaceuser.repository;

import com.Hackathon.glow.spaceuser.domain.SpaceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceUserRepository extends JpaRepository<SpaceUser, Long> {

}
