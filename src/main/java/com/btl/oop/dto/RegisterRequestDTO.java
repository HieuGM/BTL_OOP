package com.btl.oop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RegisterRequestDTO {
    @Getter@Setter
    @Size(min = 3, message = "Tên đăng nhập phải tối tối thiểu 3 kí tự.")
    private String username;

    @Getter@Setter
    @Size(min = 8, message = "Mật khẩu phải tối thiểu 8 kí tự.")
    private String password;

    @Getter@Setter
    @Size(min = 1, max = 120)
    private String firstname;

    @Getter@Setter
    @Size(min = 1, max = 120)
    private String lastname;

    @Getter@Setter
    @NotBlank @Pattern(regexp = "^0[0-9]{9}$", message = "SĐT phải bắt đầu bằng 0 và đủ 10 chữ số.")
    private String phoneNumber;

    @Getter@Setter
    @Size(min = 1, max = 255)
    private String address;

    @Getter@Setter
    @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

}
