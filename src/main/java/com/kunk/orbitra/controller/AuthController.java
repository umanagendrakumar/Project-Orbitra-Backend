package com.kunk.orbitra.controller;

import com.kunk.orbitra.dto.CreateUserDTO;
import com.kunk.orbitra.dto.LoginRequestDTO;
import com.kunk.orbitra.dto.UserResponseDTO;
import com.kunk.orbitra.model.User;
import com.kunk.orbitra.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody CreateUserDTO newUser, HttpServletRequest httpRequest) {
        User savedUser = service.register(newUser);

        authenticateAndSetSession(
                savedUser.getEmail(),
                newUser.getPassword(),
                httpRequest
        );

        UserResponseDTO user = service.getLoggedInUser(savedUser.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request, HttpServletRequest httpRequest) {
        authenticateAndSetSession(
                request.getEmail(),
                request.getPassword(),
                httpRequest
        );

        UserResponseDTO user =
                service.getLoggedInUser(request.getEmail());

        return ResponseEntity.ok(user);
    }


    private void authenticateAndSetSession(String email, String password, HttpServletRequest httpRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password)
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        httpRequest.getSession(true)
                .setAttribute(
                        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        context
                );
    }
}
