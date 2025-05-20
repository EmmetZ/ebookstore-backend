package com.sjtu.se2321.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sjtu.se2321.backend.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    @Modifying
    @Query("UPDATE CartItem SET number = :number WHERE id = :id")
    public void updateCartItem(@Param("id") Long id, @Param("number") Integer number);

    public List<CartItem> findAllByUserId(Long userId);

}