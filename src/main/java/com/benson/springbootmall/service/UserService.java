package com.benson.springbootmall.service;

import com.benson.springbootmall.dto.UserLoginRequest;
import com.benson.springbootmall.dto.UserRegisterRequest;
import com.benson.springbootmall.model.Users;

public interface UserService {

    Users getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    Users login(UserLoginRequest userLoginRequest);

}
