package com.sjtu.se2321.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.Result;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class LogoutController {

    @PutMapping("/api/logout")
    public ResponseEntity<Result<Void>> logout(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) {
        // 清除会话
        session.invalidate();

        // 清除cookie
    Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setValue("");
                    response.addCookie(cookie);
                }
            }
        }

        return ResponseEntity.ok(Result.success("Logout successful"));
    }

}
