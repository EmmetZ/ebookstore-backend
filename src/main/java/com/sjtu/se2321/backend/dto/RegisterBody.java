package com.sjtu.se2321.backend.dto;

import lombok.Data;

@Data
public class RegisterBody {
    private String username;
    private String password;
    private String email;
}
