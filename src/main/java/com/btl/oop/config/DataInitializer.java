package com.btl.oop.config;

import com.btl.oop.entity.User;
import com.btl.oop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initDatabase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder // Spring Security sẽ cung cấp cái này
    ) {
        return args -> {
            // 1. Tạo tài khoản Admin
            // Chỉ tạo nếu tài khoản "admin" chưa tồn tại
            if (userRepository.findByUsername("admin").isEmpty()) {

                // Tạo đối tượng User bằng lớp Entity
                User admin = new User();
                admin.setNickname("Admin");
                admin.setUsername("admin");

                // Rất quan trọng: Mã hóa mật khẩu trước khi lưu
                admin.setPassword(passwordEncoder.encode("admin123"));

                admin.setUserRole(User.Role.ADMIN); // Phân quyền admin
                admin.setIsActive(true);

                // Điền các trường bắt buộc khác (nếu có trong Entity)
                admin.setPhoneNumber("0123456789");
                admin.setUserAddress("Hanoi");

                userRepository.save(admin);
            }


            // ... Thêm bao nhiêu tài khoản tùy thích ...
        };
    }
}
