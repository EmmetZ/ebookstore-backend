package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Order;

public interface OrderDAO {
    public List<Order> findAllByUserId(Long userId);

    public Long addOrder(Long userId, String address, String tel, String receiver);
}
