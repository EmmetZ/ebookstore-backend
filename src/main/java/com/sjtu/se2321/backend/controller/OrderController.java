package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.OrderDTO;
import com.sjtu.se2321.backend.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/order")
    public ResponseEntity<List<OrderDTO>> getUserOrders(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        List<OrderDTO> orders = orderService.getUserOrders(userId);
        if (orders == null || orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
}
