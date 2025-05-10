package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.Tag;

@Repository
public class TagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Tag> rowMapper = new RowMapper<Tag>() {
        @Override
        public Tag mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setId(rs.getLong("id"));
            tag.setName(rs.getString("name"));
            return tag;
        }
    };

    public List<Tag> findAll() {
        return jdbcTemplate.query("SELECT * FROM Tag", rowMapper);
    }

    public List<Tag> findByBookId(Long id) {
        return jdbcTemplate.query(
                "SELECT * FROM Tag AS t JOIN BookTag AS bt ON t.id = bt.tag_id WHERE bt.book_id = ?",
                rowMapper,
                id);
    }

    public Tag findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Tag WHERE id = ?", rowMapper, id);
    }

    public Tag findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM Tag WHERE name = ?", rowMapper, name);
    }
}