package com.btl.oop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "nickname"))
public class User extends BaseEntity {

    @NotBlank(message = "nickname is required")
    @Size(min = 3, max = 50)
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @JsonIgnore
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "username is required")
    @Size(min = 1, max = 120)
    @Column(name = "username", nullable = false, length = 120)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false, length = 20)
    private Role userRole = Role.USER;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Past(message = "birthday must be in the past")
    @Column(name = "birthday")   // LocalDate -> date
    private LocalDate birthday;

    @NotBlank(message = "phone_number is required")
    @Pattern(regexp = "^0[0-9]{9}$",
            message = "phone_number must be 10 digits")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255)
    @Column(name = "user_address", length = 255)
    private String userAddress;

    public enum Role { USER, ADMIN, MODERATOR }
}
