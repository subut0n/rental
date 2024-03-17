package com.example.chatoprentalbackend.service;

import com.example.chatoprentalbackend.entity.response.AuthenticationResponse;
import com.example.chatoprentalbackend.entity.request.LoginRequest;
import com.example.chatoprentalbackend.entity.request.RegisterRequest;
import com.example.chatoprentalbackend.entity.model.User;
import com.example.chatoprentalbackend.exception.EmailAlreadyInUseException;
import com.example.chatoprentalbackend.repository.UserRepository;
import com.example.chatoprentalbackend.validation.AuthenticationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) {
        AuthenticationValidation.validateRegisterRequest(request);

        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyInUseException("Email is already used.");
        }

        User user = userService.createUser(request);
        var jwtToken =  jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        AuthenticationValidation.validateLoginRequest(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken =  jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
