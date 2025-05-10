package com.sjtu.se2321.backend.entity;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long userId;
    private Long bookId;
    private String content;
    private Integer like;
    private Long replyId;
    private OffsetDateTime createdAt;
}
