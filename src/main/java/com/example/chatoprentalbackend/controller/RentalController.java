package com.example.chatoprentalbackend.controller;


import com.example.chatoprentalbackend.entity.mapper.RentalMapper;
import com.example.chatoprentalbackend.entity.model.Rental;
import com.example.chatoprentalbackend.entity.request.CreateRentalRequest;
import com.example.chatoprentalbackend.entity.request.UpdateRentalRequest;
import com.example.chatoprentalbackend.entity.response.ListRentalResponse;
import com.example.chatoprentalbackend.entity.response.DefaultResponse;
import com.example.chatoprentalbackend.entity.response.RentalResponse;
import com.example.chatoprentalbackend.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    public RentalController(RentalService rentalService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> createRental(@RequestBody @ModelAttribute @Valid CreateRentalRequest request) {
        Optional<Rental> rentalOpt = rentalService.createRental(request);
        if (rentalOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse("User not found"));
        }
        Rental rental = rentalOpt.get();
        String message = "Rental created: " + rental.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(new DefaultResponse(message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateRental(@PathVariable Integer id, @RequestBody @ModelAttribute UpdateRentalRequest updateRentalRequest) {
        Optional<Rental> rentalOptional = rentalService.updateRental(id, updateRentalRequest);
        if (rentalOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse("Rental not found"));
        }

        String message = "Rental updated !";
        return ResponseEntity.ok(new DefaultResponse(message));
    }

    @GetMapping
    public ResponseEntity<ListRentalResponse> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        List<RentalResponse> rentalResponses = rentals.stream()
                .map(rentalMapper::toRentalResponse)
                .collect(Collectors.toList());

        ListRentalResponse listRentalResponse = new ListRentalResponse();
        listRentalResponse.setRentals(rentalResponses);

        return ResponseEntity.ok(listRentalResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id)
                .map(rentalMapper::toRentalResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
