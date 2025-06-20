package com.sjtu.se2321.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public User findByEmail(String email);

    public Page<User> findByRole(User.Role role, Pageable pageable);

}