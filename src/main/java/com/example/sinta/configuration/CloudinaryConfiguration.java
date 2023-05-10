package com.example.sinta.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfiguration {

    @Value("${cloudinary.cloudname}")
    private String cloudName;

    @Value("${cloudinary.apiKey}")
    private String apiKey;

    @Value("${cloudinary.apiSecret}")
    private String apiSecret;
    
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", this.cloudName,
            "api_key", this.apiKey,
            "api_secret", this.apiSecret,
            "secure", true
        ));
    }
}
