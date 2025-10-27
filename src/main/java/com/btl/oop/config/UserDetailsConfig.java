package com.btl.oop.config;

import com.btl.oop.entity.User;
import com.btl.oop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return (String usernameOrNickname) -> {
            // Ở đây dùng nickname để đăng nhập
            User u = userRepository.findByNickname(usernameOrNickname)
                    .orElseThrow(() -> new UsernameNotFoundException("Not found: " + usernameOrNickname));

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + u.getUserRole().name()));
            return new org.springframework.security.core.userdetails.User(
                    u.getNickname(),
                    u.getPassword(),
                    u.getIsActive(), // enabled
                    true, true, true,
                    authorities
            );
        };
    }
}
