package com.sjtu.se2321.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Result<?>> login(
            @RequestBody LoginBody body,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) {
        System.out.println("Login attempt: " + body);

        try {
            // authenticate user by username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));

            // add authentication result to the security context
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            // Save the security context in the session
            session = request.getSession(true);
            securityContextRepository.saveContext(context, request, response);

            User user = userService.findUserByUsername(body.getUsername());

            // 将用户ID存储在会话中
            session.setAttribute("userId", user.getId());

            // 创建一个cookie，保存会话ID
            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setMaxAge(3600); // 1 hour
            sessionCookie.setPath("/");
            sessionCookie.setHttpOnly(true);
            response.addCookie(sessionCookie);

            Map<String, Object> adminInfo = Map.of("role", user.getRole());
            return ResponseEntity.ok(Result.success("Login successful", adminInfo));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Result.error("login error: " + e.getMessage()));
        }
    }

}
