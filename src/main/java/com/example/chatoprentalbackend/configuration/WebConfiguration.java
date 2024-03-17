package com.example.chatoprentalbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final String uploadDir;

    public WebConfiguration(FileStorageProperties fileStorageProperties) {
        this.uploadDir = fileStorageProperties.getUploadDir();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDirPath = Paths.get(uploadDir).toFile().getAbsolutePath();
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///" + uploadDirPath + "/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
