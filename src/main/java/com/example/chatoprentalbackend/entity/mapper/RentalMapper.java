package com.example.chatoprentalbackend.entity.mapper;

import com.example.chatoprentalbackend.entity.model.Rental;
import com.example.chatoprentalbackend.entity.response.RentalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class RentalMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public RentalResponse toRentalResponse(Rental rental) {
        return RentalResponse.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(rental.getPicture())
                .description(rental.getDescription())
                .created_at(rental.getCreatedAt() != null ? rental.getCreatedAt().toLocalDateTime().format(formatter) : null)
                .updated_at(rental.getUpdatedAt() != null ? rental.getUpdatedAt().toLocalDateTime().format(formatter) : null)
                .owner_id(rental.getOwner().getId())
                .build();
    }
}
