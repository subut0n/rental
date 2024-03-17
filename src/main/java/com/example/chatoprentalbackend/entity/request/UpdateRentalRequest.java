package com.example.chatoprentalbackend.entity.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    public String name;

    @Positive
    public Integer surface;

    @PositiveOrZero
    public Integer price;

    public String description;
}
