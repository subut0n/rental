package com.example.chatoprentalbackend.controller;

import com.example.chatoprentalbackend.entity.model.Message;
import com.example.chatoprentalbackend.entity.request.MessageRequest;
import com.example.chatoprentalbackend.entity.response.DefaultResponse;
import com.example.chatoprentalbackend.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<DefaultResponse> postMessage(@RequestBody @Valid MessageRequest messageRequest) {
        messageService.createMessage(messageRequest);
        return ResponseEntity.ok(new DefaultResponse("Message posted successfully"));
    }
}
