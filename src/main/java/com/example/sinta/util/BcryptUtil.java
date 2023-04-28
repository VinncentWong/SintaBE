package com.example.sinta.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class BcryptUtil {

    public static final BcryptUtil INSTANCE = new BcryptUtil();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private BcryptUtil(){}

    public String generateBcryptPassword(String rawPassword){
        return encoder.encode(rawPassword);
    }

    public boolean comparePassword(String rawPassword, String bcryptPassword){
        return encoder.matches(rawPassword, bcryptPassword);
    }
}
