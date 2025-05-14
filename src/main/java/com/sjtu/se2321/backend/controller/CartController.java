package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.CartItemDTO;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/api/cart")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        return ResponseEntity.ok(cartService.getCartItemsByUserId(userId));
    }

    @PutMapping("/api/cart/{id}")
    public ResponseEntity<Result<Void>> updateCartItem(@PathVariable Long id, @RequestParam Integer number) {
        if (cartService.updateCartItem(id, number)) {
            return ResponseEntity.ok(Result.success("update success"));
        } else {
            return ResponseEntity.ok(Result.error("update failed"));
        }
    }

    @PutMapping("/api/cart")
    public ResponseEntity<Result<Void>> addBookToCart(@RequestParam Long bookId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        boolean res = cartService.addBookToCart(bookId, userId);
        if (res == false) {
            return ResponseEntity.ok(Result.error("add failed"));
        }
        return ResponseEntity.ok(Result.success("add success"));
    }

    @DeleteMapping("/api/cart/{id}")
    public ResponseEntity<Result<Void>> deleteCartItem(@PathVariable Long id) {
        boolean res = cartService.deleteCartItem(id);
        if (res == false) {
            return ResponseEntity.ok(Result.error("delete failed"));    
        }
        return ResponseEntity.ok(Result.success("delete success"));
    }
}
