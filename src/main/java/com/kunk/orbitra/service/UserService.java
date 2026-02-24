package com.kunk.orbitra.service;

import com.kunk.orbitra.dto.CreateUserDTO;
import com.kunk.orbitra.dto.UpdateUserDTO;
import com.kunk.orbitra.dto.UserResponseDTO;
import com.kunk.orbitra.exceptions.EmailAlreadyExistsException;
import com.kunk.orbitra.mapper.UserMapper;
import com.kunk.orbitra.model.User;
import com.kunk.orbitra.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(CreateUserDTO newUser) {
        String email = newUser.getEmail().trim().toLowerCase();

        if (repo.existsByEmail(email))
            throw new EmailAlreadyExistsException("Email already exists");

        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return repo.save(user);
    }
    public UserResponseDTO getLoggedInUser(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return userMapper.toDTO(user);
    }
    public UserResponseDTO updateLoggedInUser(UpdateUserDTO dto, Authentication authentication) {
        String email = authentication.getName();
        User existingUser = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (dto.getFirstName() != null) existingUser.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) existingUser.setLastName(dto.getLastName());
        if (dto.getEmail() != null) existingUser.setEmail(dto.getEmail());

        User userUpdated = repo.save(existingUser);

        return userMapper.toDTO(userUpdated);
    }
    public void deleteLoggedInUser(Authentication authentication) {
        String email = authentication.getName();
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        repo.delete(user);
    }
}
