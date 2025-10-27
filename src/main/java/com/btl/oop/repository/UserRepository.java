package com.btl.oop.repository;

import com.btl.oop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository
 * Data access layer for User entity
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // cho đăng nhập
    Optional<User> findByNickname(String nickname);
    boolean existsByNickname(String nickname);

    // lọc active
    Page<User> findByIsActive(Boolean isActive, Pageable pageable);

    // search đa trường
    @Query("""
       SELECT u FROM User u
       WHERE (:search IS NULL OR
              LOWER(u.username)    LIKE LOWER(CONCAT('%', :search, '%')) OR
              LOWER(u.nickname)    LIKE LOWER(CONCAT('%', :search, '%')) OR
              LOWER(u.phoneNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR
              LOWER(u.userAddress) LIKE LOWER(CONCAT('%', :search, '%'))
       )
    """)
    Page<User> findBySearchTerm(@Param("search") String search, Pageable pageable);

    // ... các hàm khác ...

    // Dùng để tìm chính xác 1 user (ví dụ: cho login)
    Optional<User> findByUsername(String username);

    // Dùng để kiểm tra xem username đã tồn tại chưa
    boolean existsByUsername(String username);
}
