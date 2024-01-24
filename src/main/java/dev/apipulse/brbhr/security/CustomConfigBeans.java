package dev.apipulse.brbhr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomConfigBeans {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Bean
//    public ObjectMapper objectMapper(){
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.configure(JsonSubTypes.Feature.FAIL_ON_REPEATED_NAMES, false);
//    }

    public static PasswordEncoder getPasswordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
