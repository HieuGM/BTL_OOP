package com.btl.oop.service;

import com.btl.oop.dto.UserRequestDTO;
import com.btl.oop.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User Service Interface
 * Business logic layer for User operations
 */
public interface UserService {
    
    /**
     * Create a new user
     */
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    
    /**
     * Get user by ID
     */
    UserResponseDTO getUserById(Long id);
    
    /**
     * Get user by username
     */
    UserResponseDTO getUserByUsername(String username);
    
    /**
     * Get all users with pagination
     */
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    
    /**
     * Search users
     */
    Page<UserResponseDTO> searchUsers(String search, Pageable pageable);
    
    /**
     * Update user
     */
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    
    /**
     * Delete user
     */
    void deleteUser(Long id);
    
    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);
}