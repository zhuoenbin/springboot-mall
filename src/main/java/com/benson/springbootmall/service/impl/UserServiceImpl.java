package com.benson.springbootmall.service.impl;

import com.benson.springbootmall.dao.UserDao;
import com.benson.springbootmall.dto.UserRegisterRequest;
import com.benson.springbootmall.model.User;
import com.benson.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

}
