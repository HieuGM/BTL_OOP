package com.btl.oop.controller;

import com.btl.oop.dto.ApiResponseDTO;
import com.btl.oop.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 * REST API endpoints for User operations
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Create a new user
     */
    @PostMapping
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        // Implementation will be added here
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.success("User created successfully", null));
    }
    
    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUserById(@PathVariable Long id) {
        // Implementation will be added here
        return ResponseEntity.ok(ApiResponseDTO.success(null));
    }
    
    /**
     * Get all users with pagination
     */
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<UserResponseDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        // Implementation will be added here
        return ResponseEntity.ok(ApiResponseDTO.success(null));
    }
    
    /**
     * Search users
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<Page<UserResponseDTO>>> searchUsers(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // Implementation will be added here
        return ResponseEntity.ok(ApiResponseDTO.success(null));
    }
    
    /**
     * Update user
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        
        // Implementation will be added here
        return ResponseEntity.ok(ApiResponseDTO.success("User updated successfully", null));
    }
    
    /**
     * Delete user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(@PathVariable Long id) {
        // Implementation will be added here
        return ResponseEntity.ok(ApiResponseDTO.success("User deleted successfully", null));
    }
}