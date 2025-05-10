package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.CommentDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.CommentDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Comment;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public PageResult<CommentDTO> getBookComments(Long bookId, int pageSize, int pageIndex, String sort) {
        int limit = pageSize;
        int offset = pageIndex * pageSize;
        List<Comment> comments = commentDAO.findBookComments(bookId, limit, offset, sort);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        User user = null;

        for (Comment comment : comments) {
            Long commentId = comment.getId();
            if (user == null) {
                Long userId = comment.getUserId();
                user = userDAO.getUserById(userId).get();
            }
            Boolean liked = commentDAO.getLikedStatus(user.getId(), commentId);
            String reply = null;
            if (comment.getReplyId() != 0) {
                reply = userDAO.getUserById(comment.getReplyId()).get().getUsername();
            }

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(commentId);
            commentDTO.setUserId(user.getId());
            commentDTO.setUsername(user.getUsername());
            commentDTO.setAvatar(user.getAvatar());
            commentDTO.setContent(comment.getContent());
            commentDTO.setLike(comment.getLike());
            commentDTO.setLiked(liked);
            commentDTO.setReply(reply);
            commentDTO.setCreatedAt(comment.getCreatedAt());

            commentDTOs.add(commentDTO);
        }
        int num = commentDAO.countBookComments(bookId);
        int total = (int) Math.ceil((double) num / limit);
        return new PageResult<>(total, commentDTOs);
    }

    @Override
    @Transactional
    public boolean likeComment(Long userId, Long commentId) {
        boolean liked = commentDAO.likeComment(userId, commentId);
        if (liked) {
            boolean status = commentDAO.updateComment(commentId, 1);
            return status;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean unlikeComment(Long userId, Long commentId) {
        boolean liked = commentDAO.unlikeComment(userId, commentId);
        if (liked) {
            boolean status = commentDAO.updateComment(commentId, -1);
            return status;
        }
        return false;
    }

}
