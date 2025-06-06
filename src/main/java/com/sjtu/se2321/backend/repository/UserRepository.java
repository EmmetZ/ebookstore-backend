package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public User findByEmail(String email);

}