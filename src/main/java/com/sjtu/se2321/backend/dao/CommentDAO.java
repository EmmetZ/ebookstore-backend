package com.sjtu.se2321.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sjtu.se2321.backend.entity.Comment;

public interface CommentDAO {

    public Page<Comment> findAllByBookId(Long bookId, Pageable pageable);

    public Boolean getLikedStatus(Long userId, Long commentId);

    public void likeComment(Long userId, Long commentId);

    public void dislikeComment(Long userId, Long commentId);

    public void updateComment(Long id, int like);

    public void save(Comment comment);

    public Comment findById(Long id);
}