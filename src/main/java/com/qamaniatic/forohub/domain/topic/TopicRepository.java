package com.qamaniatic.forohub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatusTrue(Pageable pagination);
}
