package com.sjtu.se2321.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findAllByOrderId(Long orderId);

}