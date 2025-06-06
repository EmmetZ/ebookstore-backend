package com.sjtu.se2321.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    @EntityGraph(attributePaths = { "book" })
    public List<CartItem> findAllByUserId(Long userId);

}