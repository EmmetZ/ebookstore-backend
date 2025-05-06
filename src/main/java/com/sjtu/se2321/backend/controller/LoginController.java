package com.sjtu.se2321.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.LoginBody;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Result<Void>> login(@RequestBody LoginBody body, HttpServletResponse response,
            HttpSession session) {
        System.out.println("Login attempt: " + body);

        // 验证用户名和密码
        Optional<User> userOpt = userService.validateLogin(body.getUsername(), body.getPassword());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(Result.error("Invalid username or password"));
        }

        User user = userOpt.get();

        // 将用户ID存储在会话中
        session.setAttribute("userId", user.getId());

        // 创建一个cookie，保存会话ID
        Cookie sessionCookie = new Cookie("SESSIONID", session.getId());
        sessionCookie.setMaxAge(3600); // 1 hour
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);

        return ResponseEntity.ok(Result.success("Login successful"));
    }

}
