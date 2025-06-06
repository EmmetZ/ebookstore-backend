package com.sjtu.se2321.backend.dto;

import lombok.Data;

@Data
public class AddrReqBody {
    private String tel;
    private String address;
    private String receiver;
}
