package com.example.chatoprentalbackend.exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String error;
    private String message;
}
