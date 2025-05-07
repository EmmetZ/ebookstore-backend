package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.CartItemDTO;
import com.sjtu.se2321.backend.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/api/cart")
    public List<CartItemDTO> getCartItemsByUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");
        return cartService.getCartItemsByUserId(userId);
    }
}
