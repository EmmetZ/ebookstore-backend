package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.OrderItem;

@Repository
public class OrderItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<OrderItem> rowMapper = new RowMapper<OrderItem>() {
        @Override
        public OrderItem mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(rs.getLong("id"));
            orderItem.setOrderId(rs.getLong("order_id"));
            orderItem.setBookId(rs.getLong("book_id"));
            orderItem.setNumber(rs.getInt("number"));
            return orderItem;
        };
    };

    public List<OrderItem> findAllByOrderId(Long orderId) {
        return jdbcTemplate.query("SELECT * FROM OrderItem WHERE order_id = ? ", rowMapper, orderId);
    }

    public void addOrderItem(Long orderId, Long bookId, int number) {
        jdbcTemplate.update("INSERT INTO OrderItem (order_id, book_id, number) VALUES (?, ?, ?)", orderId, bookId,
                number);
    }
}
