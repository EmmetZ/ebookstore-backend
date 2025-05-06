package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.UserAuth;

@Repository
public class UserAuthRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<UserAuth> rowMapper = new RowMapper<UserAuth>() {
        @Override
        public UserAuth mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            UserAuth userAuth = new UserAuth();
            userAuth.setId(rs.getInt("id"));
            userAuth.setPassword(rs.getString("password"));
            return userAuth;
        }
    };

    public Optional<UserAuth> findByUserId(Integer id) {
        List<UserAuth> userAuths = jdbcTemplate.query("SELECT * FROM UserAuth WHERE id = ?", rowMapper, id);
        return userAuths.isEmpty() ? Optional.empty() : Optional.of(userAuths.get(0));
    }
}