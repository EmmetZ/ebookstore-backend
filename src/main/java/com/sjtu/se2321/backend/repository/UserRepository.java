package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sjtu.se2321.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    @Modifying
    @Query(value = "UPDATE User SET balance = balance + :balance WHERE id = :userId", nativeQuery = true)
    public void updateBalance(@Param("userId") Long userId, @Param("balance") int balance);

}