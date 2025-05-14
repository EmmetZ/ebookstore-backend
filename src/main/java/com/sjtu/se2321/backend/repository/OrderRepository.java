package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.Order;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> rowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setUserId(rs.getLong("user_id"));
            order.setAddressId(rs.getLong("address_id"));
            order.setCreatedAt(rs.getObject("created_at", java.time.OffsetDateTime.class));
            return order;
        };
    };

    public List<Order> findAllByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM `Order` o WHERE user_id = ? ORDER BY created_at DESC", rowMapper, userId);
    }

}
