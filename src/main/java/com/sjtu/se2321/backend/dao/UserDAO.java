package com.sjtu.se2321.backend.dao;

import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;

public interface UserDAO {

    public User getUserByUsername(String username);

    public User getUserById(Long id);

    public UserAuth getUserAuthByUserId(Long userId);

}
