package com.sjtu.se2321.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlaceOrderBody {
    private String address;
    private String tel;
    private String receiver;
    private List<Long> itemIds;
}
