package com.sjtu.se2321.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.Utils;
import com.sjtu.se2321.backend.dto.CommentDTO;
import com.sjtu.se2321.backend.dto.CommentReqBody;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;

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
        return ResponseEntity.ok(commentService.findAllByBookId(id, pageSize, pageIndex, sort));
    }

    @PutMapping("/api/comment/{id}/like")
    public ResponseEntity<Result<Void>> likeComment(@PathVariable Long id, HttpServletRequest request) {
        Long userId = Utils.getUserId(request);
        commentService.likeComment(userId, id);
        return ResponseEntity.ok(Result.success("点赞成功"));
    }

    @PutMapping("/api/comment/{id}/unlike")
    public ResponseEntity<Result<Void>> unlikeComment(@PathVariable Long id, HttpServletRequest request) {
        Long userId = Utils.getUserId(request);
        commentService.dislikeComment(userId, id);
        return ResponseEntity.ok(Result.success("取消点赞成功"));
    }

    @PostMapping("/api/book/{id}/comments")
    public ResponseEntity<Result<Void>> postComment(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody CommentReqBody body) {
        Long userId = Utils.getUserId(request);
        commentService.save(userId, id, body.getContent());

        return ResponseEntity.ok(Result.success("发布评论成功"));
    }

    @PostMapping("/api/comment/{id}")
    public ResponseEntity<Result<Void>> reply(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody CommentReqBody body) {

        Long userId = Utils.getUserId(request);
        commentService.reply(userId, id, body.getContent());
        return ResponseEntity.ok(Result.success("回复评论成功"));
    }

}
