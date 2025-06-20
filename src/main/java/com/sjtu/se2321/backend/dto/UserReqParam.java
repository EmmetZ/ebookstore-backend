package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.User;

import lombok.Data;

@Data
public class UserReqParam {
    private Integer pageIndex;
    private Integer pageSize;
    private User.Role role;
}
