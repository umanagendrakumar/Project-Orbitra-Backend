package com.kunk.orbitra.controller;

import com.kunk.orbitra.dto.UpdateUserDTO;
import com.kunk.orbitra.dto.UserResponseDTO;
import com.kunk.orbitra.model.User;
import com.kunk.orbitra.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/user")
    public UserResponseDTO getLoggedInUser(Authentication authentication) {
        String email = authentication.getName();
        return service.getLoggedInUser(email);
    }

    @PatchMapping("/user")
    public UserResponseDTO updateLoggedInUser(
            @RequestBody UpdateUserDTO updatedUser,
            Authentication authentication) {
        return service.updateLoggedInUser(updatedUser, authentication);
    }

    @DeleteMapping("/user")
    public String deleteLoggedInUser(Authentication authentication) {
        service.deleteLoggedInUser(authentication);
        return "User Deleted";
    }
}