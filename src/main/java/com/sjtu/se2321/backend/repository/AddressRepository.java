package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.Address;

@Repository
public class AddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Address> rowMapper = new RowMapper<Address>() {
        @Override
        public Address mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setId(rs.getLong("id"));
            address.setUserId(rs.getLong("user_id"));
            address.setTel(rs.getString("tel"));
            address.setAddress(rs.getString("address"));
            address.setReceiver(rs.getString("receiver"));
            return address;
        };
    };

    public Address findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Address WHERE id = ?", rowMapper, id);
    }

    public List<Address> findAllByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM Address WHERE user_id = ?", rowMapper, userId);
    }
}
