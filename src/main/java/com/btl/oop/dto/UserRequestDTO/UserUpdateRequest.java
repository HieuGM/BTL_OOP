package com.btl.oop.dto.UserRequestDTO;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Data
public class UserUpdateRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    Long balance;
    String phoneNumber;
    String address;
}