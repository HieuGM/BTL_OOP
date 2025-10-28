package com.btl.oop.service;

import com.btl.oop.entity.User;
import com.btl.oop.exception.AppException;
import com.btl.oop.exception.ErrorCode;
import com.btl.oop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.btl.oop.dto.UserRequestDTO.*;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setBalance(request.getBalance());
        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setBalance(request.getBalance());

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}