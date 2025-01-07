package com.qamaniatic.forohub.controller;

import com.qamaniatic.forohub.domain.user.User;
import com.qamaniatic.forohub.domain.user.UserAuthenticationData;
import com.qamaniatic.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAuthenticationData userAuthenticationData) {
        // Validación de usuario existente
        if (userRepository.findByLogin(userAuthenticationData.login()) != null) {
            return ResponseEntity.badRequest().body("Usuario ya existe.");
        }

        // Validación de datos
        if (userAuthenticationData.login().isEmpty() || userAuthenticationData.password().isEmpty()) {
            return ResponseEntity.badRequest().body("Login y password no deben estar vacíos.");
        }

        // Crear y guardar nuevo usuario
        User newUser = new User(null, userAuthenticationData.login(), passwordEncoder.encode(userAuthenticationData.password()), null);
        userRepository.save(newUser);

        return ResponseEntity.created(null).body("Usuario registrado satisfactoriamente.");
    }
}
