package com.sjtu.se2321.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.CommentDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/book/{id}/comments")
    public ResponseEntity<PageResult<CommentDTO>> getBookComments(
            @PathVariable Long id,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "createdTime") String sort) {
        return ResponseEntity.ok(commentService.getBookComments(id, pageSize, pageIndex, sort));
    }

    @PutMapping("/api/comment/{id}/like")
    public ResponseEntity<Result<Void>> likeComment(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        boolean status = commentService.likeComment(userId, id);
        if (status) {
            return ResponseEntity.ok(Result.success("点赞成功"));
        } else {
            return ResponseEntity.ok(Result.error("点赞失败"));
        }
    }

    @PutMapping("/api/comment/{id}/unlike")
    public ResponseEntity<Result<Void>> unlikeComment(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        boolean status = commentService.unlikeComment(userId, id);
        if (status) {
            return ResponseEntity.ok(Result.success("取消点赞成功"));
        } else {
            return ResponseEntity.ok(Result.error("取消点赞失败"));
        }
    }
}
