package com.qamaniatic.forohub.controller;

import com.qamaniatic.forohub.domain.user.User;
import com.qamaniatic.forohub.domain.user.UserAuthenticationData;
import com.qamaniatic.forohub.infra.security.JwtTokenData;
import com.qamaniatic.forohub.infra.security.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserAuthenticationData userAuthenticationData) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(),
                userAuthenticationData.password());
        Authentication authenticationResult;
        try {
            authenticationResult = authenticationManager.authenticate(authenticationRequest);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
        var jwtToken = jwtTokenService.generateToken((User) authenticationResult.getPrincipal());
        return ResponseEntity.ok(new JwtTokenData(jwtToken));
    }

}