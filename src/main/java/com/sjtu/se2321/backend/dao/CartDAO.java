package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.CartItem;

public interface CartDAO {

    public List<CartItem> findAllByUserId(Integer userId);

}
