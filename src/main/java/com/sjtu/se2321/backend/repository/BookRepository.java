package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            book.setId(rs.getLong("id"));
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

    private Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Book", Integer.class);
    }

    public Book findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE id = ?", rowMapper, id);
    }

    public List<Book> searchBooks(int limit, int offset, Long tagId, String keyword) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT DISTINCT b.* FROM Book b ");

        int paramCount = 2;
        if (tagId != -1) {
            paramCount++;
        }
        if (keyword != null && !keyword.isEmpty()) {
            paramCount += 2;
        }
        Object[] params = new Object[paramCount];
        int index = 0;

        // 如果有标签过滤，添加 JOIN 条件
        if (tagId != -1) {
            sqlBuilder.append("JOIN BookTag bt ON b.id = bt.book_id ");
        }

        sqlBuilder.append("WHERE 1=1 ");

        // 添加tagId过滤条件
        if (tagId != -1) {
            sqlBuilder.append("AND bt.tag_id = ? ");
            params[index++] = tagId;
        }

        // 添加关键词搜索条件
        if (keyword != null && !keyword.isEmpty()) {
            sqlBuilder.append("AND (b.title LIKE ? OR b.author LIKE ? ) ");
            String searchPattern = "%" + keyword + "%";
            params[index++] = searchPattern;
            params[index++] = searchPattern;
        }

        // 添加排序和分页
        sqlBuilder.append("ORDER BY b.id ASC LIMIT ? OFFSET ?");
        params[index++] = limit;
        params[index++] = offset;

        String sql = sqlBuilder.toString();
        return jdbcTemplate.query(sql, rowMapper, params);
    }

    public Integer countSearchResult(Long tagId, String keyword) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(DISTINCT b.id) FROM Book b ");

        int paramCount = 0;
        if (tagId != -1) {
            paramCount++;
        }
        if (keyword != null && !keyword.isEmpty()) {
            paramCount += 2;
        }
        Object[] params = new Object[paramCount];
        int index = 0;

        // 如果有标签过滤，添加 JOIN 条件
        if (tagId != -1) {
            sqlBuilder.append("JOIN BookTag bt ON b.id = bt.book_id ");
        }

        sqlBuilder.append("WHERE 1=1 ");

        // 添加tagId过滤条件
        if (tagId != -1) {
            sqlBuilder.append("AND bt.tag_id = ? ");
            params[index++] = tagId;
        }

        // 添加关键词搜索条件
        if (keyword != null && !keyword.isEmpty()) {
            sqlBuilder.append("AND (b.title LIKE ? OR b.author LIKE ? ) ");
            String searchPattern = "%" + keyword + "%";
            params[index++] = searchPattern;
            params[index++] = searchPattern;
        }

        if (paramCount == 0) {
            return count();
        }
        String sql = sqlBuilder.toString();
        return jdbcTemplate.queryForObject(sql, Integer.class, params);
    }
}
