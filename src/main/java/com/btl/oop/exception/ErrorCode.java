package com.btl.oop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION (9999, "Uncategorized error"),
    INVALID_KEY             (1001, "Invalid key"),
    USER_EXISTS             (1002, "User already exists"),
    USERNAME_INVALID        (1003, "Username is invalid"),
    PASSWORD_INVALID        (1004, "Password is invalid");

    private int code;
    private String message;
}
