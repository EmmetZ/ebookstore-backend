package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.OrderItem;

public interface OrderItemDAO {
    public List<OrderItem> findAllByOrderId(Long orderId);
}
