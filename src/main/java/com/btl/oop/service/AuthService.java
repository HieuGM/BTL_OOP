package com.btl.oop.service;

import com.btl.oop.dto.RegisterForm;

public interface AuthService {
    void register(RegisterForm form); // mã hoá password, set role USER, isActive=true, lưu User
}
