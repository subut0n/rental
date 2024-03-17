package com.example.chatoprentalbackend.validation;

import com.example.chatoprentalbackend.entity.request.LoginRequest;
import com.example.chatoprentalbackend.entity.request.RegisterRequest;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationValidation {
    public static void validateLoginRequest(LoginRequest request) {
        validateEmailAndPassword(request.getEmail(), request.getPassword());
    }

    public static void validateRegisterRequest(RegisterRequest request) {
        validateEmailAndPassword(request.getEmail(), request.getPassword());
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new InvalidRequestStateException("Name must not be null or empty.");
        }
    }

    public static void validateEmailAndPassword(String email, String password) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidRequestStateException("Email must not be null and must be a valid email address.");
        }
        if (password == null || password.length() < 7) {
            throw new InvalidRequestStateException("Password must not be null and must have at least 7 characters.");
        }

    }
}
