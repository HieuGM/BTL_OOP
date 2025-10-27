package com.btl.oop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RegisterForm {
    @Getter@Setter
    @NotBlank @Size(min = 3, max = 50)
    private String nickname;

    @Getter@Setter
    @NotBlank @Size(min = 8)
    private String password;

    @Getter@Setter
    @NotBlank @Size(max = 120)
    private String username;

    @Getter@Setter
    @NotBlank @Pattern(regexp = "^0[0-9]{10}$", message = "SĐT phải bắt đầu bằng 0 và đủ 10 chữ số")
    private String phoneNumber;

    @Getter@Setter
    @NotBlank @Size(max = 255)
    private String userAddress;

    @Getter@Setter
    @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

}
