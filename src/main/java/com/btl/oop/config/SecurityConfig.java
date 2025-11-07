package com.btl.oop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration
 * Configure authentication and authorization
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * PasswordEncoder để mã hoá mật khẩu
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security Filter Chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Tạm thời tắt CSRF để test.
                // Nếu dùng Thymeleaf form, hãy bật lại
                // và thêm <input type="hidden" th:name="_csrf"...>

                .authenticationProvider(authProvider()) // Dùng provider của bạn

                .authorizeHttpRequests(auth -> auth
                        // 1. Cho phép các trang public
                        .requestMatchers("/auth/login").permitAll() // Trang login

                        // 2. CHO PHÉP CÁC URL ĐĂNG KÝ (Đây là thứ bạn thiếu lần trước)
                        .requestMatchers("/auth/register").permitAll() // <- THAY BẰNG URL ĐĂNG KÝ CỦA BẠN

                        // 3. Cho phép tài nguyên tĩnh
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // 4. Tất cả các request còn lại BẮT BUỘC phải xác thực
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/auth/login")            // GET: Spring sẽ trỏ đến đây
                        .loginProcessingUrl("/auth/login")   // POST: Spring xử lý
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout"));

        return http.build();
    }
    private final UserDetailsService userDetailsService;
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        var p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }


}
