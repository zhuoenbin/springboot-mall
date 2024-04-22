package com.benson.springbootmall.service;

import com.benson.springbootmall.dto.UserLoginRequest;
import com.benson.springbootmall.dto.UserRegisterRequest;
import com.benson.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

}
