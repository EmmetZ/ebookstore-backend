package com.sjtu.se2321.backend.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;
import com.sjtu.se2321.backend.repository.UserAuthRepository;
import com.sjtu.se2321.backend.repository.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserAuth findUserAuthById(Long userId) {
        return userAuthRepository.findById(userId).orElseThrow();
    }

    @Override
    public void updateBalance(Long userId, int balance) {
        userRepository.updateBalance(userId, balance);
    }

    @Override
    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }
}