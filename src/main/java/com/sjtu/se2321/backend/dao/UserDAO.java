package com.sjtu.se2321.backend.dao;

import java.util.Optional;

import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;

public interface UserDAO {

    public Optional<User> getUserByUsername(String username);

    public Optional<User> getUserById(Long id);

    public Optional<UserAuth> getUserAuthByUserId(Long userId);

}
