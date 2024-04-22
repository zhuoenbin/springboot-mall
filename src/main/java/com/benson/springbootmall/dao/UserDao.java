package com.benson.springbootmall.dao;

import com.benson.springbootmall.dto.UserRegisterRequest;
import com.benson.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
