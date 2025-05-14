package com.sjtu.se2321.backend.entity;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private Long userId;
    private String tel;
    private String address;
    private String receiver;
}
