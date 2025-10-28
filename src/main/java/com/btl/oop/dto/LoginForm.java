package com.btl.oop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class LoginForm {
    @Getter@Setter
    @NotBlank
    @Size(min = 3, max = 50)
    private String nickname;

    @Getter@Setter
    @NotBlank @Size(min = 8)
    private String password;
}
