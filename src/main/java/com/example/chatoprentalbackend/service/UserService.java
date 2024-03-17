package com.example.chatoprentalbackend.service;

import com.example.chatoprentalbackend.entity.request.RegisterRequest;
import com.example.chatoprentalbackend.entity.enumerate.Role;
import com.example.chatoprentalbackend.entity.model.User;
import com.example.chatoprentalbackend.exception.EmailAlreadyInUseException;
import com.example.chatoprentalbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyInUseException("Email is already used.");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        return repository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email)));
    }

    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id: " + id)));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return getUserByEmail(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUserName));
    }
}
