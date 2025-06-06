package com.sjtu.se2321.backend.dto;

import java.time.OffsetDateTime;

import com.sjtu.se2321.backend.entity.Comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long userId;
    private String username;
    private String avatar;
    private String content;
    // username
    private String reply;
    private Integer like;
    private Boolean liked;
    private OffsetDateTime createdAt;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.avatar = comment.getUser().getAvatar().getFileName();
        this.content = comment.getContent();
        this.reply = comment.getReply() != null ? comment.getReply().getUser().getUsername() : null;
        this.like = comment.getLike();
        this.createdAt = comment.getCreatedAt();
    }
}
