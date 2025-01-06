package com.qamaniatic.forohub.infra.security;

import com.qamaniatic.forohub.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Se inicia el filtro");
        if (request.getRequestURI().equals("/login") && request.getMethod().equals("POST")) {
            System.out.println("Devuelvo request y response de mi filterChain");

            // Imprimir detalles del request
            System.out.println("Request URI: " + request.getRequestURI());
            System.out.println("Request Method: " + request.getMethod());
            System.out.println("Request Headers:");
            request.getHeaderNames().asIterator().forEachRemaining(header ->
                    System.out.println(header + ": " + request.getHeader(header)));

            // Imprimir detalles del response
            System.out.println("Response Status: " + response.getStatus());
            System.out.println("Response Headers:");
            response.getHeaderNames().forEach(header ->
                    System.out.println(header + ": " + response.getHeader(header)));

            filterChain.doFilter(request, response);
            return;
        }

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var username = jwtTokenService.getSubject(token);
            if (username != null) {
                var usuario = userRepository.findByLogin(username);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
