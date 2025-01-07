package com.qamaniatic.forohub.controller;

import com.qamaniatic.forohub.domain.user.User;
import com.qamaniatic.forohub.domain.user.UserAuthenticationData;
import com.qamaniatic.forohub.domain.user.UserRegisterData;
import com.qamaniatic.forohub.domain.user.UserRepository;
import com.qamaniatic.forohub.domain.user.validations.UniqueUserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniqueUserValidator uniqueUserValidator;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegisterData userRegisterData) {
        uniqueUserValidator.validate(userRegisterData);
        User newUser = new User(null, userRegisterData.login(), passwordEncoder.encode(userRegisterData.password()), null);
        userRepository.save(newUser);

        return ResponseEntity.created(null).body("Usuario registrado satisfactoriamente.");
    }
}
