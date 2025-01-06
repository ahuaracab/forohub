package com.qamaniatic.forohub.domain.topic.validations;

import com.qamaniatic.forohub.domain.topic.TopicCreateData;

public interface TopicValidation {
    void validate(TopicCreateData data);
}
