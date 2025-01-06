package com.qamaniatic.forohub.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitle(String title);
    boolean existsByMessage(String message);
}
