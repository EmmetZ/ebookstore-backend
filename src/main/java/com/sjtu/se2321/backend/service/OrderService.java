package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.entity.Order;

public interface OrderService {
    public List<Order> findAllByUserId(Long userId);

    public void placeOrder(Long userId, String address, String tel, String receiver, List<Long> itemIds);
}
