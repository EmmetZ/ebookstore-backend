package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Comment;

public interface CommentDAO {

    public List<Comment> findBookComments(Long bookId, int limit, int offset, String sort);

    public Boolean getLikedStatus(Long userId, Long commentId);

    public Integer countBookComments(Long bookId);

    public boolean likeComment(Long userId, Long commentId);

    public boolean unlikeComment(Long userId, Long commentId);

    public boolean updateComment(Long commentId, int like);
}









