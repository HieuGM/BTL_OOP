package com.btl.oop.config;

import com.btl.oop.entity.User;
import com.btl.oop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        userRepository.findByUsername("admin").orElseGet(() -> {
            User u = new User();
            // Nếu ID của bạn là String: dùng UUID; nếu INT identity thì set theo DB của bạn
            u.setId(UUID.randomUUID().toString());
            u.setUsername("admin");
            u.setPassword(passwordEncoder.encode("admin123"));
            u.setRole("ADMIN");
            return userRepository.save(u);
        });
    }
}