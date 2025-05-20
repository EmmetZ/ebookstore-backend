package com.sjtu.se2321.backend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findAllByUserId(Long userId, Sort sort);

}