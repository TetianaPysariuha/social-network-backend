package org.finalproject.config;

import com.cloudinary.Cloudinary;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    @Value("dhbq0uk5g")
    private String cloudName;
    @Value("553129798267134")
    private String apiKey;
    @Value("a3F7FNiU3IfkmQgnf8H1uHZyWzk")
    private String apiSecret;


    @Bean
    public Cloudinary cloudinary() {

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);

        return new Cloudinary(config);
    }

}
