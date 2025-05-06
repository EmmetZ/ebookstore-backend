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

import com.sjtu.se2321.backend.entity.Tag;

@Repository
public class TagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Tag> rowMapper = new RowMapper<Tag>() {
        @Override
        public Tag mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setId(rs.getInt("id"));
            tag.setName(rs.getString("name"));
            return tag;
        }
    };

    public List<Tag> findAll() {
        return jdbcTemplate.query("SELECT * FROM Tag", rowMapper);
    }

    public Optional<List<Tag>> findByBookId(Integer id) {
        List<Tag> tags = jdbcTemplate.query(
                "SELECT * FROM Tag AS t JOIN BookTag AS bt ON t.id = bt.tag_id WHERE bt.book_id = ?",
                rowMapper,
                id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags);
    }

    public Optional<Tag> findById(Integer id) {
        List<Tag> tags = jdbcTemplate.query("SELECT * FROM Tag WHERE id = ?", rowMapper, id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }
}