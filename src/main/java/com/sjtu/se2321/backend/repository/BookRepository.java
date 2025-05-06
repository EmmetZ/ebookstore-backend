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

import com.sjtu.se2321.backend.entity.Book;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> rowMapper = new RowMapper<Book>() {
        @Override
        public Book mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getInt("price"));
            book.setDescription(rs.getString("description"));
            book.setCover(rs.getString("cover"));
            book.setSales(rs.getInt("sales"));
            return book;
        }
    };

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", rowMapper);
    }

    public Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Book", Integer.class);
    }

    public List<Book> findWithLimit(int limit, int offset) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id > ? ORDER BY id ASC LIMIT ?", rowMapper,
                offset, limit);
    }

    public Optional<Book> findById(Integer id) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", rowMapper, id);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }
}
