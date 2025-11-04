package com.btl.oop.service;

import com.btl.oop.dto.RegisterRequestDTO;

public interface AuthService {
    void register(RegisterRequestDTO form); // mã hoá password, set role USER, isActive=true, lưu User
}
