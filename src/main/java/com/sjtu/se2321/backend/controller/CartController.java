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

import com.sjtu.se2321.backend.Utils;
import com.sjtu.se2321.backend.dto.CartItemDTO;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.service.CartService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/api/cart")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByUserId(HttpServletRequest request) {
        Long userId = Utils.getUserId(request);
        return ResponseEntity.ok(cartService.findAllByUserId(userId));
    }

    @PutMapping("/api/cart/{id}")
    public ResponseEntity<Result<Void>> updateCartItem(@PathVariable Long id, @RequestParam Integer number) {
        cartService.updateCartItem(id, number);
        return ResponseEntity.ok(Result.success("update success"));
    }

    @PutMapping("/api/cart")
    public ResponseEntity<Result<Void>> addBookToCart(@RequestParam Long bookId, HttpServletRequest request) {
        Long userId = Utils.getUserId(request);
        cartService.save(bookId, userId);
        return ResponseEntity.ok(Result.success("add success"));
    }

    @DeleteMapping("/api/cart/{id}")
    public ResponseEntity<Result<Void>> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.ok(Result.success("delete success"));
    }
}
