package com.example.chatoprentalbackend.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "spring.file")
public class FileStorageProperties {
    @NotEmpty
    private String uploadDir;

}
