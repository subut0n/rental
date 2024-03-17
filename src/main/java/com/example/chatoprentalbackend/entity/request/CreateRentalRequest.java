package com.example.chatoprentalbackend.entity.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    @NotBlank
    public String name;

    @Positive
    public Integer surface;

    @PositiveOrZero
    public Integer price;

    @NotNull
    public MultipartFile picture;

    @NotBlank
    @Size(max = 2000)
    public String description;
}
