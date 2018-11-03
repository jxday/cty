package com.ame.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrytPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encodePassword("123"));
//$2a$10$HoTXduDGNbbUQnoX7WFkOuKud1Hgw5lMhf43eQD9x8sQrz6EP3/aK
    }
}
