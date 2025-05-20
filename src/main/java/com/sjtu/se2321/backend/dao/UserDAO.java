package com.sjtu.se2321.backend.dao;

import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;

public interface UserDAO {

    public User findByUsername(String username);

    public User findById(Long id);

    public UserAuth findUserAuthById(Long userId);

    public void updateBalance(Long userId, int balance);
}
