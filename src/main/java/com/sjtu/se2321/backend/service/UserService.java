package com.sjtu.se2321.backend.service;

import java.util.Optional;

import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.User;

public interface UserService {

    /**
     * 验证用户登录
     * 
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，否则返回空
     */
    public Optional<User> validateLogin(String username, String password);

    /**
     * 通过ID获取用户信息
     */
    public Optional<User> getUserById(Long userId);

    // 获取当前登录的信息
    public Optional<UserDTO> getMe(Long userId);
}