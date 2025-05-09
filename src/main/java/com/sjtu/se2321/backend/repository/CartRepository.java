package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.CartItem;

@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<CartItem> rowMapper = new RowMapper<CartItem>() {
        @Override
        public CartItem mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            CartItem cartItem = new CartItem();
            cartItem.setId(rs.getLong("id"));
            cartItem.setBookId(rs.getLong("book_id"));
            cartItem.setUserId(rs.getLong("user_id"));
            cartItem.setNumber(rs.getInt("number"));
            return cartItem;
        };
    };

    public List<CartItem> findAllByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM CartItem WHERE user_id = ? ", rowMapper, userId);
    }

    public boolean updateCartItem(Long id, Integer number) {
        int rowsAffected = jdbcTemplate.update("UPDATE CartItem SET number = ? WHERE id = ?", number, id);
        return rowsAffected > 0;
    }
}
