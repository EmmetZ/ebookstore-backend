package com.sjtu.se2321.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.RegisterBody;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.entity.Avatar;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.UserAuth;
import com.sjtu.se2321.backend.service.UserService;

@RestController
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<Result<Void>> regsiter(@RequestBody RegisterBody body) {
        if (userService.findUserByUsername(body.getUsername()) != null) {
            return ResponseEntity.ok(Result.error("用户名已被使用"));
        }
        if (userService.findByEmail(body.getEmail()) != null) {
            return ResponseEntity.ok(Result.error("该邮箱已被使用"));
        }

        User user = new User();
        user.setUsername(body.getUsername());
        user.setNickname(body.getUsername());
        user.setEmail(body.getEmail());
        user.setBalance(0);
        user.setAvatar(Avatar.defaultAvatar());
        user.setRole(User.Role.USER);
        user.setIsBanned(false);

        UserAuth userAuth = new UserAuth();
        userAuth.setPassword(passwordEncoder.encode(body.getPassword()));

        user.addUserAuth(userAuth);
        userService.save(user);
        return ResponseEntity.ok(Result.success("注册成功"));
    }

}
