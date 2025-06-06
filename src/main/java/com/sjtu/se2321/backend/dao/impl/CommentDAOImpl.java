package com.sjtu.se2321.backend.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.CommentDAO;
import com.sjtu.se2321.backend.entity.Comment;
import com.sjtu.se2321.backend.entity.CommentLike;
import com.sjtu.se2321.backend.entity.CommentLikeId;
import com.sjtu.se2321.backend.repository.CommentLikeRepository;
import com.sjtu.se2321.backend.repository.CommentRepository;

@Component
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Override
    public Page<Comment> findAllByBookId(Long bookId, Pageable pageable) {
        return commentRepository.findAllByBookId(bookId, pageable);
    }

    @Override
    public Boolean getLikedStatus(Long userId, Long commentId) {
        Optional<CommentLike> res = commentLikeRepository.findById(new CommentLikeId(userId, commentId));
        return res.isPresent();
    }

    @Override
    public void likeComment(Long userId, Long commentId) {
        commentLikeRepository.save(new CommentLike(userId, commentId));
    }

    @Override
    public void dislikeComment(Long userId, Long commentId) {
        commentLikeRepository.delete(new CommentLike(userId, commentId));
    }

    @Override
    public void updateComment(Long id, int like) {
        commentRepository.updateLike(id, like);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }
}
