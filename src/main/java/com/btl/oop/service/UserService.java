package com.btl.oop.service;

import com.btl.oop.dto.UserRequestDTO.UserCreationRequest;
import com.btl.oop.dto.UserRequestDTO.UserUpdateRequest;
import com.btl.oop.entity.User;

import java.util.List;

public interface UserService {
    public User createRequest(UserCreationRequest request);
    public User updateUser(String userId, UserUpdateRequest request);
    public void deleteUser(String userId);
    public List<User> getUsers();
    public User getUser(String id);
}
