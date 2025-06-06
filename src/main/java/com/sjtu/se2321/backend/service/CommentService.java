package com.sjtu.se2321.backend.service;

import com.sjtu.se2321.backend.dto.CommentDTO;
import com.sjtu.se2321.backend.dto.PageResult;

public interface CommentService {

    public PageResult<CommentDTO> findAllByBookId(Long bookId, int pageSize, int pageIndex, String sort);

    public void likeComment(Long userId, Long commentId);

    public void dislikeComment(Long userId, Long commentId);

    public void save(Long userId, Long bookId, String content);

    public void reply(Long userId, Long commentId, String content);
}
