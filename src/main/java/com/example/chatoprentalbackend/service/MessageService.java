package com.example.chatoprentalbackend.service;

import com.example.chatoprentalbackend.entity.model.Message;
import com.example.chatoprentalbackend.entity.model.Rental;
import com.example.chatoprentalbackend.entity.model.User;
import com.example.chatoprentalbackend.entity.request.MessageRequest;
import com.example.chatoprentalbackend.repository.MessageRepository;
import com.example.chatoprentalbackend.repository.RentalRepository;
import com.example.chatoprentalbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public void createMessage(MessageRequest messageRequest) {

        User user = userRepository.findById(messageRequest.getUser_id())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Rental rental = rentalRepository.findById(messageRequest.getRental_id())
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        Message message = Message.builder()
                .message(messageRequest.getMessage())
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .user(user)
                .rental(rental)
                .build();

        messageRepository.save(message);
    }
}
