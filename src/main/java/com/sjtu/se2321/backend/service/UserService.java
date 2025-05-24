package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.AddressDTO;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.User;

public interface UserService {

    public User findUserByUsername(String username);

    /**
     * 通过ID获取用户信息
     */
    public User findUserById(Long userId);

    // 获取当前登录的信息
    public UserDTO getMe(Long userId);

    // 获取用户地址信息
    public List<AddressDTO> findAllAddressByUserId(Long userId);
}