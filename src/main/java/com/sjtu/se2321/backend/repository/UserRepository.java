package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setNickname(rs.getString("nickname"));
            user.setBalance(rs.getString("balance"));
            user.setIntroduction(rs.getString("introduction"));
            return user;
        }
    };

    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE username = ?", rowMapper, username);
    }

    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?", rowMapper, id);
    }

    public void updateUserBalance(Long id, int balance) {
        jdbcTemplate.update("UPDATE User SET balance = balance + ? WHERE id = ?", balance, id);
    }
}