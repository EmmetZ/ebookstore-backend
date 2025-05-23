package com.sjtu.se2321.backend.controller;

import java.util.Map;

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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<Result<?>> login(
            @RequestBody LoginBody body,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) {
        System.out.println("Login attempt: " + body);

        session = request.getSession(true);

        // 验证用户名和密码
        User user = userService.validateLogin(body.getUsername(), body.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.OK).body(Result.error("Invalid username or password"));
        }

        // 将用户ID存储在会话中
        session.setAttribute("userId", user.getId());

        // 创建一个cookie，保存会话ID
        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setMaxAge(3600); // 1 hour
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);

        Map<String, Object> adminInfo = Map.of(
                "isAdmin", user.getIsAdmin() == 1 ? true : false);
        return ResponseEntity.ok(Result.success("Login successful", adminInfo));
    }

}
