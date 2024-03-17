package com.example.chatoprentalbackend.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponse {
    private Integer id;
    private String name;
    private Integer surface;
    private Integer price;
    private String picture;
    private String description;
    private Integer owner_id;
    private String created_at;
    private String updated_at;
}
