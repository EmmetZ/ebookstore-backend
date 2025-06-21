package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.Utils;
import com.sjtu.se2321.backend.dto.BookStatistic;
import com.sjtu.se2321.backend.dto.DateReqParam;
import com.sjtu.se2321.backend.dto.OrderDTO;
import com.sjtu.se2321.backend.dto.OrderReqParam;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.PlaceOrderBody;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/user/order")
    public ResponseEntity<PageResult<OrderDTO>> getUserOrders(HttpServletRequest request, OrderReqParam param) {
        Long userId = Utils.getUserId(request);
        PageResult<OrderDTO> orders = orderService.findAllByUserIdWithFilter(userId, param);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/api/order")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResult<OrderDTO>> getOrders(OrderReqParam param) {
        PageResult<OrderDTO> orders = orderService.findAllWithFilter(param);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/api/order")
    public ResponseEntity<Result<Void>> placeOrder(@RequestBody PlaceOrderBody body, HttpServletRequest request) {
        Long userId = Utils.getUserId(request);
        orderService.placeOrder(userId, body.getAddress(), body.getTel(), body.getReceiver(), body.getItemIds());
        return ResponseEntity.ok(Result.success("下单成功"));
    }

    @GetMapping("/api/user/order/statistics")
    public ResponseEntity<List<BookStatistic>> getUserOrderData(HttpServletRequest request, DateReqParam param) {
        Long userId = Utils.getUserId(request);
        return ResponseEntity.ok(orderService.gerOrderStatistic(userId, param));
    }

}
