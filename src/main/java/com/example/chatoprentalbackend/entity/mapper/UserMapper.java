package com.example.chatoprentalbackend.entity.mapper;

import com.example.chatoprentalbackend.entity.model.User;
import com.example.chatoprentalbackend.entity.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created_at(user.getCreatedAt() != null ? formatter.format(user.getCreatedAt().toLocalDateTime()) : null)
                .updated_at(user.getUpdatedAt() != null ? formatter.format(user.getUpdatedAt().toLocalDateTime()) : null)
                .build();
    }
}
