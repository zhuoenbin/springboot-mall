package com.benson.springbootmall.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequest {

    @NotBlank  //表示不能是null也不能是空白字串
    private String email;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
