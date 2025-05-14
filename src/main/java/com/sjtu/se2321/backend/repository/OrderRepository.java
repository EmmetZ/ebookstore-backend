package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
            order.setAddress(rs.getString("address"));
            order.setTel(rs.getString("tel"));
            order.setReceiver(rs.getString("receiver"));
            order.setCreatedAt(rs.getObject("created_at", java.time.OffsetDateTime.class));
            return order;
        };
    };

    public List<Order> findAllByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM `Order` o WHERE user_id = ? ORDER BY created_at DESC", rowMapper,
                userId);
    }

    public Long addOrder(Long userId, String address, String tel, String receiver) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO `Order` (user_id, address, tel, receiver) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setLong(1, userId);
            ps.setString(2, address);
            ps.setString(3, tel);
            ps.setString(4, receiver);
            return ps;
        }, keyHolder);
        
        Number id = keyHolder.getKey();
        if (id == null) {
            throw new RuntimeException("Failed to retrieve ID for newly created order");
        }
        return id.longValue();
    }
}
