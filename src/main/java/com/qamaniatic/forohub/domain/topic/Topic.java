package com.qamaniatic.forohub.domain.topic;

import com.qamaniatic.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Topic(TopicCreateData topicCreateData, User user) {
        this.title = topicCreateData.title();
        this.message = topicCreateData.message();
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
        this.status = true;
    }

    public void updateTopic(TopicUpdateData topicUpdateData) {
        if (topicUpdateData.title() != null) {
            this.title = topicUpdateData.title();
        }
        if (topicUpdateData.message() != null) {
            this.message = topicUpdateData.message();
        }
    }

    public void deactivateTopic() {
        this.status = false;
    }
}

