package com.example.chatoprentalbackend.repository;

import com.example.chatoprentalbackend.entity.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
