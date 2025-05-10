package com.sjtu.se2321.backend.dto;

import java.time.OffsetDateTime;

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
}
