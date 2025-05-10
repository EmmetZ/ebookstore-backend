package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.CommentDAO;
import com.sjtu.se2321.backend.entity.Comment;
import com.sjtu.se2321.backend.repository.CommentRepository;

@Component
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findBookComments(Long bookId, int limit, int offset, String sort) {
        return commentRepository.findBookComments(bookId, limit, offset, sort);
    }

    @Override
    public Boolean getLikedStatus(Long userId, Long commentId) {
        return commentRepository.getLikedStatus(userId, commentId);
    }

    @Override
    public Integer countBookComments(Long bookId) {
        return commentRepository.countBookComments(bookId);
    }

    @Override
    public boolean likeComment(Long userId, Long commentId) {
        return commentRepository.likeComment(userId, commentId);
    }

    @Override
    public boolean unlikeComment(Long userId, Long commentId) {
        return commentRepository.unlikeComment(userId, commentId);
    }

    @Override
    public boolean updateComment(Long commentId, int like) {
        return commentRepository.updateComment(commentId, like);
    }
}
