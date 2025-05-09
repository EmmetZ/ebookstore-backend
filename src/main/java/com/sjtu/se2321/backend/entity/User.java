package com.sjtu.se2321.backend.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String nickname;
    private String balance;
    private String introduction;
    private String avatar;
}