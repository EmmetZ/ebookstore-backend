package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.AddressDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.AddressDTO;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.Address;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;
import com.sjtu.se2321.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Override
    public User validateLogin(String username, String password) {
        // 根据用户名查找用户
        User user = userDAO.findByUsername(username);

        if (user == null) {
            return null;
        }

        // 获取用户认证信息
        UserAuth userAuth = userDAO.findUserAuthById(user.getId());

        if (userAuth == null) {
            return null;
        }

        // 验证密码是否匹配
        if (password.equals(userAuth.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public User findUserById(Long userId) {
        return userDAO.findById(userId);
    }

    @Override
    public UserDTO getMe(Long userId) {
        if (userId != null) {
            User user = userDAO.findById(userId);
            if (user != null) {
                return UserDTO.fromUser(user);
            }
        }
        return null;
    }

    public List<AddressDTO> findAllAddressByUserId(Long userId) {
        List<Address> addressList = addressDAO.findAllByUserId(userId);
        List<AddressDTO> result = new ArrayList<>();
        for (Address addr : addressList) {
            result.add(new AddressDTO(addr));
        }
        return result;
    }

}