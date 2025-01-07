package com.qamaniatic.forohub.domain.user.validations;

import com.qamaniatic.forohub.domain.ValidationException;
import com.qamaniatic.forohub.domain.user.UserRegisterData;
import com.qamaniatic.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUserValidator implements UserValidation {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserRegisterData userRegisterData) {
        if (userRepository.findByLogin(userRegisterData.login()) != null) {
            throw new ValidationException("Nombre de usuario ya existe.", "login");
        }
    }
}
