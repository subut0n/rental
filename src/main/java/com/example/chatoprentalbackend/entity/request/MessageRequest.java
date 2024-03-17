package com.example.chatoprentalbackend.entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequest {
    @NotBlank
    @Size(min = 1, max = 2000)
    private String message;

    @Positive
    private Integer user_id;

    @Positive
    private Integer rental_id;

}
