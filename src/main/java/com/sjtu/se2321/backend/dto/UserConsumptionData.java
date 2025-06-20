package com.sjtu.se2321.backend.dto;

import lombok.Data;

@Data
public class UserConsumptionData {
    private AdminUserDTO user;
    private Integer consumption;
}
