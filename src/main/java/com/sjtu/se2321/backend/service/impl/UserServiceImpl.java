package com.sjtu.se2321.backend.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;
import com.sjtu.se2321.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Optional<User> validateLogin(String username, String password) {
        // 根据用户名查找用户
        Optional<User> userOpt = userDAO.getUserByUsername(username);

        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();

        // 获取用户认证信息
        Optional<UserAuth> userAuthOpt = userDAO.getUserAuthByUserId(user.getId());

        if (userAuthOpt.isEmpty()) {
            return Optional.empty();
        }

        UserAuth userAuth = userAuthOpt.get();

        // 验证密码是否匹配
        if (password.equals(userAuth.getPassword())) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public Optional<UserDTO> getMe(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            Optional<User> userOpt = userDAO.getUserById(userId);
            if (userOpt.isPresent()) {
                return Optional.of(UserDTO.fromUser(userOpt.get()));
            }
        }
        return Optional.empty();
    }

}