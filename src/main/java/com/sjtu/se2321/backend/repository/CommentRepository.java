package com.sjtu.se2321.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sjtu.se2321.backend.entity.Comment;

@Repository
public class CommentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Comment> rowMapper = new RowMapper<Comment>() {
        @Override
        public Comment mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setBookId(rs.getLong("book_id"));
            comment.setContent(rs.getString("content"));
            comment.setLike(rs.getInt("like"));
            comment.setReplyId(rs.getLong("reply_id"));
            comment.setCreatedAt(rs.getObject("created_at", OffsetDateTime.class));
            return comment;
        }
    };

    public List<Comment> findBookComments(Long bookId, int limit, int offset, String sort) {
        if (sort.equals("createdTime")) {
            return jdbcTemplate.query(
                    "SELECT c.* FROM Comment c WHERE book_id = ? ORDER BY c.created_at DESC LIMIT ? OFFSET ?",
                    rowMapper, bookId, limit, offset);

        } else {
            return jdbcTemplate.query(
                    "SELECT c.* FROM Comment c WHERE book_id = ? ORDER BY c.like DESC LIMIT ? OFFSET ?",
                    rowMapper, bookId, limit, offset);
        }
    }

    public Boolean getLikedStatus(Long userId, Long commentId) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM CommentLike WHERE user_id = ? AND comment_id = ?",
                    Integer.class, userId, commentId);
            return count != null && count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer countBookComments(Long bookId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Comment WHERE book_id = ?", Integer.class, bookId);
    }

    public boolean likeComment(Long userId, Long commentId) {
        try {
            int row = jdbcTemplate.update(
                    "INSERT INTO CommentLike (user_id, comment_id) VALUES (?, ?)",
                    userId, commentId);
            return row == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean unlikeComment(Long userId, Long commentId) {
        try {
            int row = jdbcTemplate.update(
                    "DELETE FROM CommentLike WHERE user_id = ? AND comment_id = ?",
                    userId, commentId);
            return row == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateComment(Long commentId, int like) {
        try {
            int row = jdbcTemplate.update(
                    "UPDATE Comment SET `like` = `like` + ? WHERE id = ?",
                    like, commentId);
            return row == 1;
        } catch (Exception e) {
            return false;
        }
    }

}
