package com.btl.oop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
    private final org.springframework.security.core.userdetails.UserDetailsService userDetailsService;
    public SecurityConfig(org.springframework.security.core.userdetails.UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    // Security configuration will be implemented here
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // hoặc delegating như trên
    }
    @Bean
    public DaoAuthenticationProvider authProvider() {
        var p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // bật lại nếu dùng Thymeleaf + thêm CSRF token vào form
                .authenticationProvider(authProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")            // GET: hiển thị form
                        .loginProcessingUrl("/auth/login")   // POST: Spring xử lý
                        .usernameParameter("nickname")       // tên input trong form
                        .passwordParameter("password")       // tên input trong form
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}