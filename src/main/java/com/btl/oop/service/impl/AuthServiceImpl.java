//package com.btl.oop.service.impl;
//
//import com.btl.oop.dto.LoginForm;
//import com.btl.oop.entity.User;
//import com.btl.oop.repository.UserRepository;
//import com.btl.oop.service.AuthService;
//import com.btl.oop.dto.RegisterForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void register(RegisterForm form) {
//        // Chuẩn hoá input
//        final String nickname = form.getNickname().trim();
//        final String username = form.getUsername().trim();
//        final String phone    = form.getPhoneNumber() == null ? null : form.getPhoneNumber().trim();
//        final String address  = form.getUserAddress() == null ? null : form.getUserAddress().trim();
//
//        // Kiểm tra trùng nickname (DB đang unique nickname)
//        if (userRepository.existsByNickname(nickname)) {
//            throw new IllegalArgumentException("Nickname đã tồn tại");
//        }
//
//        // Map DTO -> Entity
//        User user = new User();
//        user.setNickname(nickname);
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(form.getPassword())); // hash mật khẩu!
//        user.setPhoneNumber(phone);
//        user.setUserAddress(address);
//        user.setBirthday(form.getBirthday());
//        user.setUserRole(User.Role.USER);   // mặc định USER
//        user.setIsActive(true);             // kích hoạt tài khoản
//
//        userRepository.save(user);
//    }
//
//}
