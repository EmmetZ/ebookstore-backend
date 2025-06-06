package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.CommentDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.CommentDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Comment;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BookDAO bookDAO;

    @Override
    public PageResult<CommentDTO> findAllByBookId(Long bookId, int pageSize, int pageIndex, String sort) {
        Pageable pageable;
        if (sort.equals("createdTime")) {
            pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        } else {
            pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "like"));
        }
        Page<Comment> comments = commentDAO.findAllByBookId(bookId, pageable);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments.getContent()) {
            User user = comment.getUser();
            Boolean liked = commentDAO.getLikedStatus(user.getId(), comment.getId());
            CommentDTO commentDTO = new CommentDTO(comment);
            commentDTO.setLiked(liked);
            commentDTOs.add(commentDTO);
        }

        return new PageResult<CommentDTO>(comments.getTotalPages(), commentDTOs);
    }

    @Override
    @Transactional
    public void likeComment(Long userId, Long commentId) {
        commentDAO.likeComment(userId, commentId);
        commentDAO.updateComment(commentId, 1);
    }

    @Override
    @Transactional
    public void dislikeComment(Long userId, Long commentId) {
        commentDAO.dislikeComment(userId, commentId);
        commentDAO.updateComment(commentId, -1);
    }

    @Override
    @Transactional
    public void save(Long userId, Long bookId, String content) {
        Book book = bookDAO.getReferenceById(bookId);
        User user = userDAO.getReferenceById(userId);
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setUser(user);
        comment.setContent(content);
        comment.setLike(0);
        commentDAO.save(comment);
    }

    @Override
    @Transactional
    public void reply(Long userId, Long commentId, String content) {
        User user = userDAO.getReferenceById(userId);
        Comment comment = commentDAO.findById(commentId);
        Comment reply = new Comment();
        reply.setBook(comment.getBook());
        reply.setUser(user);
        reply.setContent(content);
        reply.setLike(0);
        reply.setReply(comment);
        commentDAO.save(reply);
    }
}
