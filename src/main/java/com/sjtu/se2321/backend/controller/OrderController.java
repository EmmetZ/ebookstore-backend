package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.OrderDTO;
import com.sjtu.se2321.backend.dto.PlaceOrderBody;
import com.sjtu.se2321.backend.dto.Result;
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
        List<OrderDTO> orders = orderService.findAllByUserId(userId);
        if (orders == null || orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/api/order")
    public ResponseEntity<Result<Void>> placeOrder(@RequestBody PlaceOrderBody body, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        orderService.placeOrder(userId, body.getAddress(), body.getTel(), body.getReceiver(), body.getItemIds());
        return ResponseEntity.ok(Result.success("下单成功"));
    }

}
