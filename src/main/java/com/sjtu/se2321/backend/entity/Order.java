package com.sjtu.se2321.backend.entity;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long userId;
    private String address;
    private String tel;
    private String receiver;
    private OffsetDateTime createdAt;
}
