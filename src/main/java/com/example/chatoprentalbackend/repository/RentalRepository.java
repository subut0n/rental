package com.example.chatoprentalbackend.repository;

import com.example.chatoprentalbackend.entity.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
