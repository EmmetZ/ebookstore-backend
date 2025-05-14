package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.OrderDTO;

public interface OrderService {
    public List<OrderDTO> getUserOrders(Long userId);
}
