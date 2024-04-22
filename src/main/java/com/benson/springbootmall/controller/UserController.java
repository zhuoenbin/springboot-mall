package com.benson.springbootmall.controller;

import com.benson.springbootmall.dto.UserLoginRequest;
import com.benson.springbootmall.dto.UserRegisterRequest;
import com.benson.springbootmall.model.Users;
import com.benson.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register") //註冊新專案
    public ResponseEntity<Users> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        Users user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login") //登入
    public ResponseEntity<Users> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        Users user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
