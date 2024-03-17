package com.example.chatoprentalbackend.service;

import com.example.chatoprentalbackend.entity.model.Rental;
import com.example.chatoprentalbackend.entity.model.User;
import com.example.chatoprentalbackend.entity.request.CreateRentalRequest;
import com.example.chatoprentalbackend.entity.request.UpdateRentalRequest;
import com.example.chatoprentalbackend.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final FileStorageService fileStorageService;

    public List<Rental> getAllRentals() {
        return this.rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Integer id) {
        return this.rentalRepository.findById(id);
    }

    public Optional<Rental> createRental(CreateRentalRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        String fileDownloadUri = fileStorageService.storeFile(request.getPicture());

        Optional<User> ownerOptional = userService.getUserByEmail(email);
        if (ownerOptional.isEmpty()) {
            return Optional.empty();
        }
        User owner = ownerOptional.get();

        Rental rental = Rental.builder()
                .name(request.getName())
                .surface(request.getSurface())
                .price(request.getPrice())
                .picture(fileDownloadUri)
                .description(request.getDescription())
                .owner(owner)
                .build();

        return Optional.of(rentalRepository.saveAndFlush(rental));
    }

    public Optional<Rental> updateRental(Integer rentalID, UpdateRentalRequest request) {
        Optional<Rental> rentalOptional = this.rentalRepository.findById(rentalID);
        if (rentalOptional.isEmpty()) {
            return Optional.empty();
        }

        Rental rental = rentalOptional.get();
        rental.setName(request.getName());
        rental.setSurface(request.getSurface());
        rental.setPrice(request.getPrice());
        rental.setDescription(request.getDescription());
        rentalRepository.saveAndFlush(rental);

        return Optional.of(rental);
    }
}
