package com.qamaniatic.forohub.domain.user.validations;

import com.qamaniatic.forohub.domain.user.UserRegisterData;

public interface UserValidation {
    void validate(UserRegisterData data);
}
