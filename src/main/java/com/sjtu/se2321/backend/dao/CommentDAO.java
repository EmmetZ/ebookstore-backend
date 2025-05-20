package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Comment;

public interface CommentDAO {

    public List<Comment> findAllByBookId(Long bookId, int limit, int offset, String sort);

    public Boolean getLikedStatus(Long userId, Long commentId);

    public Integer countByBookId(Long bookId);

    public void likeComment(Long userId, Long commentId);

    public void dislikeComment(Long userId, Long commentId);

    public void updateComment(Long id, int like);
}