package com.sjtu.se2321.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.Result;

import jakarta.servlet.http.HttpSession;

@RestController
public class LogoutController {

    @PutMapping("/logout")
    public ResponseEntity<Result<Void>> logout(HttpSession session) {
        // 清除会话
        session.invalidate();
        return ResponseEntity.ok(Result.success("Logout successful"));
    }

}
