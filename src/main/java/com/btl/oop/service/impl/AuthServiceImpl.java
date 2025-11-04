package com.btl.oop.service.impl;

import com.btl.oop.dto.LoginRequestDTO;
import com.btl.oop.dto.RegisterRequestDTO;
import com.btl.oop.entity.User;
import com.btl.oop.repository.UserRepository;
import com.btl.oop.service.AuthService;
import com.btl.oop.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(RegisterRequestDTO form) {
        // Chuẩn hoá input
        final String username = form.getUsername().trim();
        final String phone    = form.getPhoneNumber() == null ? null : form.getPhoneNumber().trim();
        final String address  = form.getAddress() == null ? null : form.getAddress().trim();
        final String firstname = form.getFirstname().trim();
        final String lastname = form.getLastname().trim();
        final LocalDate dob = form.getDob();
        // Kiểm tra trùng
        if (userRepository.existsByUsername(username) ) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }

        // Map DTO -> Entity
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(form.getPassword())); // hash mật khẩu!
        user.setPhoneNumber(phone);
        user.setAddress(address);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setDob(dob);
        user.setRole(String.valueOf(User.Role.USER));// mặc định USER
        user.setBalance(Long.valueOf(0));
        user.setIsActive(true);             // kích hoạt tài khoản

        userRepository.save(user);
    }

}

