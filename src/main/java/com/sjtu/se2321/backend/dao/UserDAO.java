package com.sjtu.se2321.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;

public interface UserDAO {

    public User findByUsername(String username);

    public User findById(Long id);

    public UserAuth findUserAuthById(Long userId);

    public User getReferenceById(Long id);

    public void save(User user);

    public User findByEmail(String email);

    public Page<User> findByRole(User.Role role, Pageable pageable);
}
