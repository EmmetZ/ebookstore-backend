package com.sjtu.se2321.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sjtu.se2321.backend.entity.Order;

public interface OrderDAO {
    public List<Order> findAllByUserId(Long userId);

    public void save(Order order);

    public Page<Order> findAll(Specification<Order> spec, Pageable pageable);

    public List<Order> findAll(Specification<Order> spec);
}
