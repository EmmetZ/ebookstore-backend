package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.AddressDTO;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/me")
    public ResponseEntity<UserDTO> getMe(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        UserDTO user = userService.getMe(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/user/me/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddress(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        List<AddressDTO> addresses = userService.findAllAddressByUserId(userId);
        return ResponseEntity.ok(addresses);
    }
}
