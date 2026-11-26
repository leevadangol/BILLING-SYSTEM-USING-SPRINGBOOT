package com.billing.config;

import com.billing.model.AppUser;
import com.billing.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Creates the default login (admin / admin123) if it does not exist yet. */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createDefaultAdmin(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (!users.existsByUsername("admin")) {
                users.save(new AppUser("admin", encoder.encode("admin123")));
            }
        };
    }
}
