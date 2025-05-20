package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

}