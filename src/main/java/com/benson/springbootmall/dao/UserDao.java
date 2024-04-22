package com.benson.springbootmall.dao;

import com.benson.springbootmall.dto.UserRegisterRequest;
import com.benson.springbootmall.model.Users;

public interface UserDao {

    Users getUserById(Integer userId);

    Users getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
