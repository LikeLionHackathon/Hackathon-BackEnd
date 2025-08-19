package com.Hackathon.glow.tag.repository;

import com.Hackathon.glow.tag.domain.Tag;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);

    boolean existsByTagName(String tagName);
}