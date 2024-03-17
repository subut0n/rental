package com.example.chatoprentalbackend.controller;

import com.example.chatoprentalbackend.entity.mapper.UserMapper;
import com.example.chatoprentalbackend.entity.model.User;
import com.example.chatoprentalbackend.entity.response.AuthenticationResponse;
import com.example.chatoprentalbackend.entity.request.LoginRequest;
import com.example.chatoprentalbackend.entity.request.RegisterRequest;
import com.example.chatoprentalbackend.entity.response.UserResponse;
import com.example.chatoprentalbackend.service.AuthenticationService;
import com.example.chatoprentalbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.toUserResponse(currentUser));
    }

}
